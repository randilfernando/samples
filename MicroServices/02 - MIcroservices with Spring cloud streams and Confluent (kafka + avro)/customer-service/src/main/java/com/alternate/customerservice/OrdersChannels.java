package com.alternate.customerservice;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * Class where message channes for order schema is defined
 * MessageChannel attributes where defines in application.yml
 * See: ${spring.cloud.stream.bindings.orders-in} entry
 */
public interface OrdersChannels {
    String INPUT = "orders-in";

    @Output(OrdersChannels.INPUT)
    SubscribableChannel input();
}

