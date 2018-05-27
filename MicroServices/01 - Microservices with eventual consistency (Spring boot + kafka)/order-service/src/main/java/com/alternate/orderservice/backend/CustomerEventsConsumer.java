package com.alternate.orderservice.backend;

import com.alternate.orderservice.domain.events.CustomerCreditReservationFailedEvent;
import com.alternate.orderservice.domain.events.CustomerCreditReservedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerEventsConsumer {

    /**
     * use to serialize java object into json string
     */
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private OrderService orderService;

    @Autowired
    public CustomerEventsConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * listen to customer topic
     */
    @KafkaListener(topics = "customer")
    public void receiveMessage(String message) {
        /** 
         * when there is a new message in order topic it will come here
         * need to extract event from message
         */
        int pos = message.indexOf('#');
        String eventName = message.substring(0, pos);
        String eventBody = message.substring(pos + 1);

        switch (eventName) {
            case "customer_credit_reserved_event":
                try {
                    orderService.handleCustomerCreditReservedEvent(objectMapper.readValue(eventBody, CustomerCreditReservedEvent.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "customer_credit_reservation_failed_event":
                try {
                    orderService.handleCustomerCreditReservationFailedEvent(objectMapper.readValue(eventBody, CustomerCreditReservationFailedEvent.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
