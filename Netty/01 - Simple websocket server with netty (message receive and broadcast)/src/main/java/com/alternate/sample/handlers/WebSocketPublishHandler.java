package com.alternate.sample.handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class WebSocketPublishHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPublishHandler.class.getSimpleName());
    private final ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("Adding new channel {} to list of channels", ctx.channel().remoteAddress());
        allChannels.add(ctx.channel());
    }

    public void broadcastMessage(String content) {
        LOGGER.info("Broadcast message: {}", content);

        TextWebSocketFrame msg = new TextWebSocketFrame(content);
        allChannels.forEach(ch -> {
            LOGGER.info("Sending to channel {}", ch.remoteAddress());
            msg.retain();
            ch.writeAndFlush(msg.duplicate());
        });

        ReferenceCountUtil.release(msg);
    }
}
