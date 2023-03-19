package com.example.springboot.demo.syntest.mastertest;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    private ConcurrentLinkedQueue<MyTask> queue = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<String,Object> taskResultMap = new ConcurrentHashMap<>();

    public ConcurrentLinkedQueue<MyTask> getQueue() {
        return queue;
    }

    public void setQueue(ConcurrentLinkedQueue<MyTask> queue) {
        this.queue = queue;
    }

    public ConcurrentHashMap<String, Object> getTaskResultMap() {
        return taskResultMap;
    }

    public void setTaskResultMap(ConcurrentHashMap<String, Object> taskResultMap) {
        this.taskResultMap = taskResultMap;
    }
    private Object handle(MyTask myTask){
        try {
            System.out.println(Thread.currentThread().getName()+"开始处理task :"+myTask.getName());
            TimeUnit.SECONDS.sleep(1);
            return new Random().nextInt(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void run() {
        while(true){
            MyTask myTask = queue.poll();
            if (myTask == null) break;
            Object obj = handle(myTask);
            taskResultMap.put(myTask.getId(),obj);
        }
    }
}
