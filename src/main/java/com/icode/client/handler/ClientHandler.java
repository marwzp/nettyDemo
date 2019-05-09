package com.icode.client.handler;

import com.icode.protocol.request.LoginRequestPacket;
import com.icode.protocol.Packet;
import com.icode.protocol.PacketCodeC;
import com.icode.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * 客户端处理器
 * @author wangz
 * @date 2019/5/7 - 17:53
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当建立连接成功，执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);

    }

    /**
     * 读取服务器返回数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {

            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                if (loginResponsePacket.isSuccess()) {
                    System.out.println(new Date() + ": 客户端登录成功");
                } else {
                    System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
                }

            }


        }

    }
}
