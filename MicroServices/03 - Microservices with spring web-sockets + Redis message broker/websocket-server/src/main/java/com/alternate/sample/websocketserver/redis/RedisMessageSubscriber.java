package com.alternate.sample.websocketserver.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    private final SimpMessagingTemplate template;

    @Autowired
    public RedisMessageSubscriber(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        logger.info("Received a message: {}", message.toString());
        template.convertAndSend("/topic/chat", message.toString());
    }
}
