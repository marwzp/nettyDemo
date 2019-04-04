package com.icode.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import sun.awt.SunHints;

/**
 * @author wangz
 * @date 2019/4/4 - 10:11
 **/
public class NettyServer {

    private final static int PORT = 8000;

    public static  void main(String[] args){

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                 //设置两个线程组，boosGroup负责监听接收新的线程组
                 //workerGroup表示处理每一条连接的数据读写的线程组
                .group(boosGroup,workGroup)
                //设置IO模型为Nio模型连接
                .channel(NioServerSocketChannel.class)
                //绑定处理器，处理每一条连接的业务处理逻辑
                .handler(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                    }
                })
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //通过chanel的attr获取到设置的属性值
                        System.out.println(ch.attr(clientKey).get());
                    }
                });

        //绑定端口号
        bind(serverBootstrap,PORT);


    }


    /**
     * 自动递增绑定端口
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>(){
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {

                if(future.isSuccess()){
                    System.out.println("端口[" + port + "]绑定成功!");
                }else{
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }

            }
        });
    }

}
