package com.alternate.orderservice.domain.events;

public class OrderRejectedEvent {

    private Integer orderId;

    public OrderRejectedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
