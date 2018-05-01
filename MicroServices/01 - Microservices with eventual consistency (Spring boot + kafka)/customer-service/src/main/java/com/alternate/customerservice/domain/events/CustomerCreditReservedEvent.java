package com.alternate.customerservice.domain.events;

public class CustomerCreditReservedEvent {

    private Integer orderId;

    public CustomerCreditReservedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
