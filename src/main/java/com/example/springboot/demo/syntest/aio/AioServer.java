package com.example.springboot.demo.syntest.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AioServer {
    public AsynchronousServerSocketChannel socketChannel;
    public void init(int port){
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            socketChannel = AsynchronousServerSocketChannel.open(group);
            socketChannel.bind(new InetSocketAddress("127.0.0.1",port));
            System.out.println("绑定成功.....");
            socketChannel.accept(this,new ServerHandle());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AioServer aioServer = new AioServer();
        aioServer.init(8765);
    }
}
