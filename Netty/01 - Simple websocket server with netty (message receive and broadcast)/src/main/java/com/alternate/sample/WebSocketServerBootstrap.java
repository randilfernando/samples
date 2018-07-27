package com.alternate.sample;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.Timer;
import java.util.TimerTask;

public class WebSocketServerBootstrap {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        WebSocketServer webSocketServer = new WebSocketServer(4000, bossGroup, workerGroup);
        webSocketServer.start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                webSocketServer.broadcastMessage("Scheduled message from server");
            }
        }, 5000, 5000);

        Thread mainThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            webSocketServer.shutdown();
            try {
                mainThread.join();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
