package com.alternate.orderservice.domain.events;

public class CustomerCreditReservationFailedEvent {

    private Integer orderId;

    public CustomerCreditReservationFailedEvent() {
    }

    public CustomerCreditReservationFailedEvent(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
