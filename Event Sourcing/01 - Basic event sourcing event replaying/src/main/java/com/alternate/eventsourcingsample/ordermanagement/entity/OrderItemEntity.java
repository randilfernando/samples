package com.alternate.eventsourcingsample.ordermanagement.entity;

public class OrderItemEntity {
    private String code;
    private String name;

    private Integer quantity;
    private Double unitPrice;
    private Double price;

    public OrderItemEntity(String code, String name, Integer quantity, Double unitPrice, Double price) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getPrice() {
        return price;
    }
}
