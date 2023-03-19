package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.UUID;

/**
 * 生产者，用于生成数据
 */
public class Producer {
    private Disruptor<OrderData> disruptor;
    private RingBuffer<OrderData> ringBuffer;
    public Producer(Disruptor<OrderData> disruptor){
        this.disruptor = disruptor;
    }
    public Producer(RingBuffer<OrderData> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    public void addData(OrderEventTranslator orderEventTranslator){
        disruptor.publishEvent(orderEventTranslator);
    }
    public void addData(){
        long seq=ringBuffer.next();
        OrderData orderData = ringBuffer.get(seq);
        String id = UUID.randomUUID().toString();
        orderData.setId(id);
        orderData.setOrderNumber(seq+"");
        int price = new Random().nextInt(5000);
        orderData.setPrice(price);
        ringBuffer.publish(seq);
    }
}
