package com.example.springboot.demo.syntest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
    private List<String> list;
    private AtomicInteger count = new AtomicInteger(0);
    private int minSize = 0;
    private String lock = "lock";
    private int maxSize;
    public MyQueue(int size){
        this.maxSize = size;
        this.list = new LinkedList<>();
    }
    public void put(String str){
        try {
            synchronized (lock){
                while(count.get()==this.maxSize){
                    lock.wait();
                }
                list.add(str);
                count.incrementAndGet();
                System.out.println("新加入的元素为:"+str);
                lock.notify();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String take(){
        String str = null;
        try {
            synchronized (lock){
                while(this.count.get()==0){
                    lock.wait();
                }
                str = list.remove(0);
                count.decrementAndGet();
                System.out.println("移除元素为:"+str);
                lock.notify();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
    public int getQueueSize(){
        return count.get();
    }

    public static void main(String[] args) {
        final MyQueue queue = new MyQueue(5);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        queue.put("5");
        System.out.println("queueSize:"+queue.getQueueSize());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 6;i<11;i++){
                        queue.put(i+"");
                        System.out.println("queueSize:"+queue.getQueueSize());
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0;i<5;i++){
                        String str = queue.take();
                        System.out.println("queueSize:"+queue.getQueueSize());
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
