package com.alternate.orderservice.domain.events;

import com.alternate.orderservice.domain.Order;

public class OrderCreatedEvent {

    private Integer orderId;

    private Order orderDetails;

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
