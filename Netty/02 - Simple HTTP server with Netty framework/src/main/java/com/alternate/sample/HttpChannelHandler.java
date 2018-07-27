package com.alternate.sample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        printRequest(request);

        String responseMessage = "{\"status\": 200, \"message\": \"success\"}";

        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(responseMessage.getBytes())
        );

        if (HttpUtil.isKeepAlive(request)) {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseMessage.length());

        ctx.writeAndFlush(response);
    }

    private void printRequest(FullHttpRequest request) {
        ByteBuf buf = request.content();
        int contentLength = buf.readableBytes();

        StringBuilder sb = new StringBuilder();

        sb
                .append("Request received\n")
                .append("Uri: ")
                .append(request.uri())
                .append("\n")
                .append("Method: ")
                .append(request.method().asciiName())
                .append("\n")
                .append("Content: ");

        if (contentLength > 0) {
            sb.append(buf.readCharSequence(contentLength, CharsetUtil.UTF_8).toString());
        } else {
            sb.append("No content");
        }

        System.out.println(sb.toString());
    }
}
