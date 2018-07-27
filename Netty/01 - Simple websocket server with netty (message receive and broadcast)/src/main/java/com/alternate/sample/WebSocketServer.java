package com.alternate.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class.getSimpleName());
    private WebSocketServerChannelInitializer webSocketServerChannelInitializer = new WebSocketServerChannelInitializer();
    private int port;

    public WebSocketServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            LOGGER.info("Bootstrapping WebSocket server");

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(webSocketServerChannelInitializer);

            LOGGER.info("Initialize WebSocket server to listen on port: {}", port);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();

            LOGGER.info("Server closed successfully");
        } catch (InterruptedException e) {
            LOGGER.error("Exception when syncing threads {}", e);
        } finally {
            LOGGER.info("Shutdown event loop groups");

            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

            LOGGER.info("Event loop groups shutdown successfully");
        }
    }

    public void broadcastMessage(String message) {
        webSocketServerChannelInitializer.getWebSocketPublishHandler().broadcastMessage(message);
    }
}
