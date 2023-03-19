package com.example.springboot.demo.syntest.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class ServerHandle implements CompletionHandler<AsynchronousSocketChannel,AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        attachment.socketChannel.accept(attachment,this);
        this.read(result);
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
    }
    public void read(AsynchronousSocketChannel socketChannel){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                String con = new String(attachment.array()).trim();
                System.out.println("收到client消息:"+con);
                write(socketChannel);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }
    public void write(AsynchronousSocketChannel socketChannel){
        try {
            String con = "hhhhhhh";
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(con.getBytes());
            buffer.flip();
            socketChannel.write(buffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
