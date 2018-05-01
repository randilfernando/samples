package com.alternate.orderservice.domain.events;

public class CustomerCreditReservedEvent {

    private Integer orderId;

    public CustomerCreditReservedEvent() {
    }

    public CustomerCreditReservedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
