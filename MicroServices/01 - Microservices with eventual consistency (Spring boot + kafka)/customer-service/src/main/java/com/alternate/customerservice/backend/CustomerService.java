package com.alternate.customerservice.backend;


import com.alternate.customerservice.domain.Customer;
import com.alternate.customerservice.domain.events.OrderCreatedEvent;

import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerByCustomerId(Integer id);

    boolean handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent);
}
