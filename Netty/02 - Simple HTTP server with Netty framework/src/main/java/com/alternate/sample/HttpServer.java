package com.alternate.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class.getSimpleName());

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public HttpServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        this.port = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    public void start() {
        try {
            LOGGER.info("Bootstrapping http server");

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpServerChannelInitializer());

            LOGGER.info("Initialize http server to listen on port: {}", port);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("Exception when syncing threads {}", e);
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        LOGGER.info("Shutdown event loop groups");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
