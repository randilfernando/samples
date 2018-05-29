package com.alternate.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(OrdersChannels.class)
public class CustomerService {

    public static void main(String[] args) {
        SpringApplication.run(CustomerService.class);
    }
}
