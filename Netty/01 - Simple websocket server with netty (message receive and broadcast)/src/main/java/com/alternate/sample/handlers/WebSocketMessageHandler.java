package com.alternate.sample.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketMessageHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessageHandler.class.getSimpleName());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) {
        if (msg instanceof TextWebSocketFrame) {
            String content = ((TextWebSocketFrame) msg).text();
            LOGGER.info("Received text frame {}", content);
        } else {
            // For now, let's ignore binary frames
            throw new UnsupportedOperationException("Invalid WebSocket frame received");
        }
    }
}
