package com.alternate.customerservice.domain;

public class Order {

    private Integer orderId;

    private Integer customerId;

    private Double orderTotal;

    private String orderState;

    public Order() {
    }

    public Order(Integer customerId, Double orderTotal, String orderState) {
        this.customerId = customerId;
        this.orderTotal = orderTotal;
        this.orderState = orderState;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public String getOrderState() {
        return orderState;
    }
}
