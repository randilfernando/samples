package com.alternate.customerservice.backend;

import com.alternate.customerservice.domain.events.OrderCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    /**
     * use to serialize java object into json string
     */
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private CustomerService customerService;

    @Autowired
    public KafkaConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * listen to order topic
     */
    @KafkaListener(topics = "order")
    public void receiveMessage(String message) {
        /** 
         * when there is a new message in order topic it will come here
         * need to extract event from message
         */
        int pos = message.indexOf('#');
        String eventName = message.substring(0, pos);
        String eventBody = message.substring(pos + 1);

        switch (eventName) {
            case "order_created_event":
                try {
                    customerService.handleOrderCreatedEvent(objectMapper.readValue(eventBody, OrderCreatedEvent.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
