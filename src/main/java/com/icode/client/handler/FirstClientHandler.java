package com.icode.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 业务处理器
 * 继承ChannelInboundHandlerAdapter 适配器
 * @author wangz
 * @date 2019/5/7 - 14:46
 **/
public class FirstClientHandler extends ChannelInboundHandlerAdapter{

    /**
     * 客户端建立连接成功后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");
        //获取数据
        ByteBuf buffer = getByteBuf(ctx);
        //写出数据:从上下文中获取到channel，将数据写到channel
        ctx.channel().writeAndFlush(buffer);
    }

    /**
     * 获取ByteBuf
     * @param ctx
     * @return
     */
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，垃圾!".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }


    /**
     * 读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

    }
}
