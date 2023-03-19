package com.example.springboot.demo.syntest.disruptortest;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.scheduling.concurrent.DefaultManagedAwareThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {
    /**
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(6);
        Disruptor disruptor = new Disruptor<OrderData>(new OrderEventFactory(),
                1024,
                new DefaultManagedAwareThreadFactory(),
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());
        disruptor.handleEventsWith(new Consumer());
        disruptor.start();
        Producer producer = new Producer(disruptor);
        OrderEventTranslator orderEventTranslator = new OrderEventTranslator();
        for (int i = 0;i<50;i++){
            //生产100条数据
            producer.addData(orderEventTranslator);
            //Thread.sleep(50);
        }
        disruptor.shutdown();
    }
    */

    /**
    public static void main(String[] args) {
        RingBuffer ringBuffer = RingBuffer.createSingleProducer(new OrderEventFactory(),1024,new BusySpinWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        WorkerPool workerPool = new WorkerPool<OrderData>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),new Consumer());
        workerPool.start(executorService);
        Producer producer = new Producer(ringBuffer);
        for (int i = 0;i<10;i++){
            //生产100条数据
            producer.addData();
            //Thread.sleep(50);
        }
//        workerPool.halt();
        executorService.shutdown();
    }*/
    public static void main(String[] args) {

    }
}
