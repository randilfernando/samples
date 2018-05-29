package com.alternate.customerservice;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Class where message channels for order schema is defined
 * MessageChannel attributes where defines in application.yml
 * See: ${spring.cloud.stream.bindings.orders-in} entry
 */
public interface MessageChannels {
    String ORDERS_IN = "orders-in";

    @Input(MessageChannels.ORDERS_IN)
    SubscribableChannel ordersIn();
}
