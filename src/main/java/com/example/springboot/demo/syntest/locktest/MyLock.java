package com.example.springboot.demo.syntest.locktest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    public void handle1(){
        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"进入handle1....");
            Thread.sleep(1000);
            condition.await();
            System.out.println(threadName+"离开handle1....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void handle2(){
        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"进入handle2....");
            Thread.sleep(2000);
            condition.await();
            System.out.println(threadName+"离开handle2....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void handle3(){
        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"进入handle3....");
            Thread.sleep(2000);
            condition.signalAll();
            System.out.println(threadName+"离开handle3....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void handle4(){
        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"进入handle4....");
            Thread.sleep(2000);
            condition2.await();
            System.out.println(threadName+"离开handle4....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void handle5(){
        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+"进入handle5....");
            Thread.sleep(2000);
            condition2.signal();
            System.out.println(threadName+"离开handle5....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        try {
            final MyLock myLock = new MyLock();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    myLock.handle1();
                }
            },"t1");
            t1.start();

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    myLock.handle2();
                }
            },"t2");
            t2.start();

            Thread t4 = new Thread(new Runnable() {
                @Override
                public void run() {
                    myLock.handle4();
                }
            },"t4");
            t4.start();

            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    myLock.handle3();
                }
            },"t3");
            Thread.sleep(2000);
            t3.start();

            Thread t5 = new Thread(new Runnable() {
                @Override
                public void run() {
                    myLock.handle5();
                }
            },"t5");
            Thread.sleep(5000);
            t5.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
