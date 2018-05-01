package com.alternate.customerservice.domain.events;

import com.alternate.customerservice.domain.Customer;

public class CustomerCreatedEvent {

    private Integer customerId;

    private Customer customerDetails;

    public CustomerCreatedEvent(Integer customerId, Customer customerDetails) {
        this.customerId = customerId;
        this.customerDetails = customerDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(Customer customerDetails) {
        this.customerDetails = customerDetails;
    }
}
