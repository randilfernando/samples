package com.alternate.customerservice.webapi;

public class CreateCustomerResponse {

    private Integer customerId;

    public CreateCustomerResponse(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}
