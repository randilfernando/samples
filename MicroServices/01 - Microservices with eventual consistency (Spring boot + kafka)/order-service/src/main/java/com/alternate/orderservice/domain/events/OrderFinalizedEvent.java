package com.alternate.orderservice.domain.events;

public class OrderFinalizedEvent {

    private Integer orderId;

    public OrderFinalizedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
