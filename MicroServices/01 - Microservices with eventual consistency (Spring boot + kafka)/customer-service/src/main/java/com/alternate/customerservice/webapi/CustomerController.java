package com.alternate.customerservice.webapi;

import com.alternate.customerservice.backend.CustomerService;
import com.alternate.customerservice.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<CreateCustomerResponse> createOrder(@RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer(createCustomerRequest.getCustomerName(), createCustomerRequest.getCreditLimit());
        customerService.createCustomer(customer);
        CreateCustomerResponse createCustomerResponse = new CreateCustomerResponse(customer.getCustomerId());
        return ResponseEntity.ok(createCustomerResponse);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderById(@PathVariable("id") Integer customerId) {
        Optional<Customer> customer = customerService.getCustomerByCustomerId(customerId);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
