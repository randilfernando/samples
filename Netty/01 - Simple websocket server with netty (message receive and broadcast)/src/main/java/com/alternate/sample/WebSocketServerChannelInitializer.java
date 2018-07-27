package com.alternate.sample;

import com.alternate.sample.handlers.WebSocketPublishHandler;
import com.alternate.sample.handlers.WebSocketMessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.stomp.StompSubframeDecoder;

public class WebSocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final WebSocketPublishHandler webSocketPublishHandler = new WebSocketPublishHandler();

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new WebSocketServerCompressionHandler())
                .addLast(new WebSocketServerProtocolHandler("/", null, true))
                .addLast(webSocketPublishHandler)
                .addLast(new WebSocketMessageHandler());
    }

    public WebSocketPublishHandler getWebSocketPublishHandler() {
        return webSocketPublishHandler;
    }
}
