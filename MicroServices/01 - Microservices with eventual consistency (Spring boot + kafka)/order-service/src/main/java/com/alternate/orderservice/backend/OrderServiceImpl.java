package com.alternate.orderservice.backend;

import com.alternate.orderservice.domain.Order;
import com.alternate.orderservice.domain.events.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    /**
     * use to serialize java object into json string
     */
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private OrderRepository orderRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Order createOrder(Order order) {
        orderRepository.save(order);

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(order.getOrderId(), order);
        try {
            kafkaTemplate.send("order", "order_created_event#" + objectMapper.writeValueAsString(orderCreatedEvent));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public Optional<Order> getOrderByOrderId(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void handleCustomerCreditReservedEvent(CustomerCreditReservedEvent customerCreditReservedEvent) {
        Optional<Order> order = orderRepository.findById(customerCreditReservedEvent.getOrderId());

        if (order.isPresent()) {
            Order orderData = order.get();
            orderData.setOrderState("finalized");
            orderRepository.save(orderData);

            OrderFinalizedEvent orderFinalizedEvent = new OrderFinalizedEvent(orderData.getOrderId());
            try {
                kafkaTemplate.send("order", "order_finalized_event#" + objectMapper.writeValueAsString(orderFinalizedEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleCustomerCreditReservationFailedEvent(CustomerCreditReservationFailedEvent customerCreditReservationFailedEvent) {
        Optional<Order> order = orderRepository.findById(customerCreditReservationFailedEvent.getOrderId());

        if (order.isPresent()) {
            Order orderData = order.get();
            orderData.setOrderState("rejected");
            orderRepository.save(orderData);

            OrderRejectedEvent orderRejectedEvent = new OrderRejectedEvent(orderData.getOrderId());
            try {
                kafkaTemplate.send("order", objectMapper.writeValueAsString(orderRejectedEvent));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
