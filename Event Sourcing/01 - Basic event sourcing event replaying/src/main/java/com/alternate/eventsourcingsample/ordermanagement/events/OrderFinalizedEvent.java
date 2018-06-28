package com.alternate.eventsourcingsample.ordermanagement.events;

public class OrderFinalizedEvent extends OrderEvent {
    public final Double payment;

    public OrderFinalizedEvent(String orderId, Double payment) {
        super(orderId, "orderFinalizedEvent");
        this.payment = payment;
    }

    public Double getPayment() {
        return payment;
    }
}
