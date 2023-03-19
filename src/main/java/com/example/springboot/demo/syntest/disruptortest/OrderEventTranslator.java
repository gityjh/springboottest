package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;
import java.util.UUID;

/**
 * 订单数据封装翻译家
 */
public class OrderEventTranslator implements EventTranslator<OrderData> {
    @Override
    public void translateTo(OrderData orderData, long l) {
        String id = UUID.randomUUID().toString();
        orderData.setId(id);
        orderData.setOrderNumber(l+"");
        int price = new Random().nextInt(5000);
        orderData.setPrice(price);
    }
}
