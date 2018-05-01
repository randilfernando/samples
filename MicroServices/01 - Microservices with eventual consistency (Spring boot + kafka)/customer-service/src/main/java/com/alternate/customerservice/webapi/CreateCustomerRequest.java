package com.alternate.customerservice.webapi;

public class CreateCustomerRequest {

    private String customerName;

    private Double creditLimit;

    public String getCustomerName() {
        return customerName;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }
}
