package com.alternate.sample.websocketserver.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfiguration {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageSubscriber redisMessageSubscriber) {
        return new MessageListenerAdapter(redisMessageSubscriber);
    }
}
