package com.example.springboot.demo.syntest.nettytest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyMainTest {
    public void init(){
        try {
            EventLoopGroup connect = new NioEventLoopGroup();
            EventLoopGroup handle = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(connect,handle)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(8765).sync();
            //绑定多个端口
            //ChannelFuture future2 = serverBootstrap.bind(8764).sync();
            //等待关闭
            future.channel().closeFuture().sync();
            //资源释放
            connect.shutdownGracefully();
            handle.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NettyMainTest nettyMainTest = new NettyMainTest();
        nettyMainTest.init();
    }
}
