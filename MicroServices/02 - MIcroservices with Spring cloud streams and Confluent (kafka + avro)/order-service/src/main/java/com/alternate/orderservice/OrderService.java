package com.alternate.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
@EnableBinding(MessageChannels.class)
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}
}
