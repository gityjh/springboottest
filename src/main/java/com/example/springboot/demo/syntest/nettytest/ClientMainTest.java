package com.example.springboot.demo.syntest.nettytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMainTest {
    public void init(){
        try {
            EventLoopGroup workgroup = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(workgroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new ClientHandler());
                        }
                    });

            ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();
            //连接多个端口
            //ChannelFuture cf2 = b.connect("127.0.0.1", 8764).sync();
            //buf
            cf1.channel().writeAndFlush(Unpooled.copiedBuffer("你好".getBytes()));
            cf1.channel().closeFuture().sync();
            workgroup.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientMainTest clientMainTest = new ClientMainTest();
        clientMainTest.init();
    }
}
