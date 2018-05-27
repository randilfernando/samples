package com.alternate.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBinding(Source.class)
@EnableSchemaRegistryClient
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}
}
