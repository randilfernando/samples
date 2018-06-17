package com.alternate.sample.redispublisher;

import redis.clients.jedis.Jedis;

import java.util.Timer;
import java.util.TimerTask;

public class RedisPublisher {

    public static void main(String[] args) {
        String  redisHost = System.getenv("REDIS_HOST") != null
                ? System.getenv("REDIS_HOST")
                : "localhost";

        Jedis jPublish = new Jedis(redisHost);
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jPublish.publish("chat", "Hello from redis publisher");
            }
        }, 0, 4000);
    }
}
