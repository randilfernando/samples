package com.alternate.customerservice.domain.events;

import com.alternate.customerservice.domain.Order;

public class OrderCreatedEvent {

    private Integer orderId;

    private Order orderDetails;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Integer orderId, Order orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Order getOrderDetails() {
        return orderDetails;
    }
}
