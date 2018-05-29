package com.alternate.orderservice;

import com.alternate.schemas.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    private Source source;

    @Autowired
    public OrderController(Source source) {
        this.source = source;
    }

    @PostMapping
    public ResponseEntity<?> produce(@RequestBody Order order){
        source.output().send(MessageBuilder.withPayload(order).build());
        return ResponseEntity.ok().build();
    }
}
