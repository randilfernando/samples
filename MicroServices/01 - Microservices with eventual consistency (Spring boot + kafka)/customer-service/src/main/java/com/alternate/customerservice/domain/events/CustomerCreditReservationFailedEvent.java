package com.alternate.customerservice.domain.events;

public class CustomerCreditReservationFailedEvent {

    private Integer orderId;

    public CustomerCreditReservationFailedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
