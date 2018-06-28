package com.alternate.eventsourcingsample.ordermanagement.entity;

import com.alternate.eventsourcingsample.ordermanagement.events.OrderEvent;
import com.alternate.eventsourcingsample.ordermanagement.events.ItemAddedEvent;
import com.alternate.eventsourcingsample.ordermanagement.events.OrderCreatedEvent;
import com.alternate.eventsourcingsample.ordermanagement.events.OrderFinalizedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class OrderEntity {
    private String orderId;
    private String customerId;
    private Double payment;
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    public void apply(OrderEvent event) {
        if (event instanceof OrderCreatedEvent) {
            apply((OrderCreatedEvent) event);
        } else if (event instanceof ItemAddedEvent) {
            apply((ItemAddedEvent) event);
        } else if (event instanceof OrderFinalizedEvent) {
            apply((OrderFinalizedEvent) event);
        }
    }

    private void apply(OrderCreatedEvent event){
        customerId = event.customerId;
    }

    private void apply(ItemAddedEvent event) {
        orderItems.add(event.item);
    }

    private void apply(OrderFinalizedEvent event) {
        payment = event.payment;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Double getPayment() {
        return payment;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
