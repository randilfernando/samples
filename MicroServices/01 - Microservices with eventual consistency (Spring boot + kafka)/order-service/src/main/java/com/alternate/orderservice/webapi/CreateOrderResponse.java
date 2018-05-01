package com.alternate.orderservice.webapi;

public class CreateOrderResponse {

    private Integer orderId;

    public CreateOrderResponse(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
