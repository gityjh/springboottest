package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 消费者，用于消费数据
 */
public class Consumer implements EventHandler<OrderData>, WorkHandler<OrderData> {
    @Override
    public void onEvent(OrderData orderData, long l, boolean b) throws Exception {
        this.onEvent(orderData);
    }

    @Override
    public void onEvent(OrderData orderData) throws Exception {
        System.out.println("1111:"+Thread.currentThread().getName()+":"+orderData);
        Thread.sleep(100);
    }
}
