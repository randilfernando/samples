package com.alternate.customerservice;

import com.alternate.schemas.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
public class OrderEventsListenService {

    private final Log logger = LogFactory.getLog(getClass());

    @StreamListener(Sink.INPUT)
    public void orderCreated(Order order){
        logger.info("Order created for customer id: " + order.getCustomerId());
        logger.info(order);
    }
}
