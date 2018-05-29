package com.alternate.orderservice;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Class where message channels for order schema is defined
 * MessageChannel attributes where defines in application.yml
 * See: ${spring.cloud.stream.bindings.orders-out} entry
 */
public interface MessageChannels {
    String ORDERS_OUT = "orders-out";

    @Output(MessageChannels.ORDERS_OUT)
    MessageChannel ordersOut();
}
