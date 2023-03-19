package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.EventFactory;

public class OrderEventFactory implements EventFactory<OrderData> {
    @Override
    public OrderData newInstance() {
        return new OrderData();
    }
}
