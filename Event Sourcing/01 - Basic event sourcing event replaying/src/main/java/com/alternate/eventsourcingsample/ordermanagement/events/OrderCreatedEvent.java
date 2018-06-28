package com.alternate.eventsourcingsample.ordermanagement.events;

public class OrderCreatedEvent extends OrderEvent {
    public final String customerId;

    public OrderCreatedEvent(String orderId, String customerId) {
        super(orderId, "orderCreatedEvent");
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }
}