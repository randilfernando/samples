package com.alternate.sample;

import java.util.Timer;
import java.util.TimerTask;

public class WebSocketServerBootstrap {
    public static void main(String[] args) {
        WebSocketServer webSocketServer = new WebSocketServer(4000);
        webSocketServer.start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                webSocketServer.broadcastMessage("Scheduled message from server");
            }
        }, 5000, 5000);
    }
}
