package com.alternate.customerservice.backend;

import com.alternate.customerservice.domain.CreditReservation;
import com.alternate.customerservice.domain.Customer;
import com.alternate.customerservice.domain.events.CustomerCreatedEvent;
import com.alternate.customerservice.domain.events.CustomerCreditReservationFailedEvent;
import com.alternate.customerservice.domain.events.CustomerCreditReservedEvent;
import com.alternate.customerservice.domain.events.OrderCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * use to serialize java object into json string
     */
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private CustomerRepository customerRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.save(customer);
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(customer.getCustomerId(), customer);
        try {
            kafkaTemplate.send("customer", "customer_created_event#" + objectMapper.writeValueAsString(customerCreatedEvent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Optional<Customer> getCustomerByCustomerId(Integer id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public boolean handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        boolean state = false;
        Optional<Customer> customer = customerRepository.findById(orderCreatedEvent.getOrderDetails().getCustomerId());
        Customer customerData;
        if (customer.isPresent()) {
            customerData = customer.get();
            Double reservedCredit = 0.0;
            for (CreditReservation creditReservation: customerData.getCreditReservations()) {
                reservedCredit += creditReservation.getAmount();
            }

            if (reservedCredit + orderCreatedEvent.getOrderDetails().getOrderTotal() < customerData.getCreditLimit()) {
                CreditReservation creditReservation = new CreditReservation(orderCreatedEvent.getOrderDetails().getCustomerId(), orderCreatedEvent.getOrderId(), orderCreatedEvent.getOrderDetails().getOrderTotal());
                customerData.addCreditReservation(creditReservation);
                customerRepository.save(customerData);
                state = true;
            }
        }

        if (state) {
            CustomerCreditReservedEvent customerCreditReservedEvent = new CustomerCreditReservedEvent(orderCreatedEvent.getOrderId());
            try {
                kafkaTemplate.send("customer", "customer_credit_reserved_event#" + objectMapper.writeValueAsString(customerCreditReservedEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            CustomerCreditReservationFailedEvent customerCreditReservationFailedEvent = new CustomerCreditReservationFailedEvent(orderCreatedEvent.getOrderId());
            try {
                kafkaTemplate.send("customer", "customer_credit_reservation_failed_event#" + objectMapper.writeValueAsString(customerCreditReservationFailedEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return state;
    }
}
