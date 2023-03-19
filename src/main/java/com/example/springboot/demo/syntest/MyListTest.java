package com.example.springboot.demo.syntest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyListTest {
    private volatile List<String> list = new ArrayList<>(20);
    public void addList(){
        list.add("yjh");
    }
    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        final MyListTest myListTest = new MyListTest();
//        String lock = "lock";
        CountDownLatch downLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    synchronized (lock){
                        for (int i=0;i<10;i++){
                            myListTest.addList();
                            System.out.println(Thread.currentThread().getName()+" add 元素........");
                            if(myListTest.getSize()==5){
                                //lock.notify();
                                System.out.println(Thread.currentThread().getName()+" 发送通知........");
                                downLatch.countDown();
                            }
                            Thread.sleep(500);
                        }
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName()+" 进入等待");
                        //lock.wait();
                        downLatch.await();
    //                    if(myListTest.getSize()==5){
                        System.out.println(Thread.currentThread().getName()+ " 收到通知........");
//                    }
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        t2.start();
        t1.start();
    }
}
