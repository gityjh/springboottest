package com.example.springboot.demo.syntest.locktest;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    public void readHandle(){
        try {
            String threadName = Thread.currentThread().getName();
            readLock.lock();
            System.out.println(threadName+"进入readHandle");
            Thread.sleep(1500);
            System.out.println(threadName+"离开readHandle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
    public void writeHandle(){
        try {
            String threadName = Thread.currentThread().getName();
            writeLock.lock();
            System.out.println(threadName+"进入writeHandle");
            Thread.sleep(1000);
            System.out.println(threadName+"离开writeHandle");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final MyReadWriteLock readWriteLock = new MyReadWriteLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readHandle();
            }
        },"t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readHandle();
            }
        },"t2");
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.writeHandle();
            }
        },"t3");
        t3.start();
    }
}
