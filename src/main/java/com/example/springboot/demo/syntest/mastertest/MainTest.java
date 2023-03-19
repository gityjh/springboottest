package com.example.springboot.demo.syntest.mastertest;

import org.springframework.util.StringUtils;

import java.rmi.server.UID;

public class MainTest {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("可用处理器数量:"+processors);
        Master master = new Master(new Worker(),2);
        for (int i=1;i<=50;i++){
            MyTask myTask = new MyTask("task"+i,"任务"+i);
            master.addTask(myTask);
        }
        long start = System.currentTimeMillis();
        master.execute();
        while (true){
            if(master.isComplete()){
                long end = System.currentTimeMillis();
               int result = master.getTaskResult();
               System.out.println("result:"+result+",耗时:"+(end-start));
               break;
            }
        }
    }
}
