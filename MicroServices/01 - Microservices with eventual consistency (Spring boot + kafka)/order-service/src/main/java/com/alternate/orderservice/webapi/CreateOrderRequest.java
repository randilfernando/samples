package com.alternate.orderservice.webapi;

public class CreateOrderRequest {

    private Integer customerId;

    private Double orderTotal;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Double orderTotal, Integer customerId) {
        this.orderTotal = orderTotal;
        this.customerId = customerId;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}
