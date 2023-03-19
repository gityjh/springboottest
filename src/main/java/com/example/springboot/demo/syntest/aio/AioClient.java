package com.example.springboot.demo.syntest.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class AioClient implements Runnable{
    private AsynchronousSocketChannel socketChannel;
    public void connect(int port){
        try {
            socketChannel = AsynchronousSocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String con){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(con.getBytes());
            buffer.flip();
            socketChannel.write(buffer).get();
            read();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public void read(){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer).get();
            buffer.flip();
            byte[] readByte = new byte[buffer.remaining()];
            buffer.get(readByte);
            String con = new String(readByte);
            System.out.println("收到server的消息:"+con);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while(true){
            int i = 0;
        }
    }

    public static void main(String[] args) {
        try {
            AioClient aio1 = new AioClient();
            AioClient aio2 = new AioClient();
            AioClient aio3 = new AioClient();
            new Thread(aio1).start();
            new Thread(aio2).start();
            new Thread(aio3).start();
            aio1.connect(8765);
            Thread.sleep(1000);
            aio1.write("aio1 11111");

            aio2.connect(8765);
            Thread.sleep(1000);
            aio2.write("aio2 22222");

            aio3.connect(8765);
            Thread.sleep(1000);
            aio3.write("aio3 33333");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
