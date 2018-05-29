package com.alternate.orderservice;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Class where message channes for order schema is defined
 * MessageChannel attributes where defines in application.yml
 * See: ${spring.cloud.stream.bindings.orders-out} entry
 */
public interface OrdersChannels {
    String OUTPUT = "orders-out";

    @Output(OrdersChannels.OUTPUT)
    MessageChannel output();
}

