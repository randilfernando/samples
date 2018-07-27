package com.alternate.sample;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class HttpServerBootstrap {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        HttpServer httpServer = new HttpServer(8080, bossGroup, workerGroup);
        httpServer.start();
    }
}
