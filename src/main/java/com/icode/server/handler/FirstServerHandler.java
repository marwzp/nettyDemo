package com.icode.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author wangz
 * @date 2019/5/7 - 14:58
 **/
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据到客户端
        System.out.println(new Date() + ": 服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，你也是垃圾啊!".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }


}
