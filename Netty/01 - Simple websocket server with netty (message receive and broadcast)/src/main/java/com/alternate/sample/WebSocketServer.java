package com.alternate.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseCombiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class.getSimpleName());
    private WebSocketServerChannelInitializer webSocketServerChannelInitializer = new WebSocketServerChannelInitializer();

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private Channel ch;

    public WebSocketServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    @Override
    public void run() {
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
            ch = f.channel();
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("Exception when syncing threads {}", e);
        } finally {
            LOGGER.info("Shutdown event loop groups");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void shutdown() {
        ch.close();
    }

    public void broadcastMessage(String message) {
        webSocketServerChannelInitializer.getWebSocketPublishHandler().broadcastMessage(message);
    }
}
