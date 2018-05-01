package com.alternate.customerservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "credit_reservations")
public class CreditReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    private Integer customerId;

    private Integer orderId;

    private Double amount;

    public CreditReservation() {
    }

    public CreditReservation(Integer customerId, Integer orderId, Double amount) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
