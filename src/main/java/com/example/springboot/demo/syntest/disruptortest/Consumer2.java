package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class Consumer2  implements EventHandler<OrderData>, WorkHandler<OrderData> {
    @Override
    public void onEvent(OrderData orderData, long l, boolean b) throws Exception {
        this.onEvent(orderData);
    }

    @Override
    public void onEvent(OrderData orderData) throws Exception {
        System.out.println("2222:"+Thread.currentThread().getName()+":"+orderData);
    }
}
