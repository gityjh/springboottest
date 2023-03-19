package com.example.springboot.demo.syntest.mastertest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    private ConcurrentLinkedQueue<MyTask> queue = new ConcurrentLinkedQueue<>();
    private Map<String,Thread> workerMap = new HashMap<>();
    private ConcurrentHashMap<String,Object> taskResultMap = new ConcurrentHashMap<>();
    public Master(Worker worker,int count){
        for (int i=1;i<=count;i++){
            worker.setQueue(this.queue);
            worker.setTaskResultMap(this.taskResultMap);
            Thread thread = new Thread(worker,"worker"+i);
            workerMap.put("worker"+i,thread);
        }
    }
    public void addTask(MyTask myTask){
        queue.add(myTask);
    }
    public void execute(){
        for (Map.Entry<String,Thread> worker:workerMap.entrySet()) {
            worker.getValue().start();
        }
    }
    public boolean isComplete(){
        for (Map.Entry<String,Thread> worker:workerMap.entrySet()) {
            if(worker.getValue().getState()!= Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    public int getTaskResult(){
        int sum = 0;
        for (Map.Entry<String,Object> result:taskResultMap.entrySet()) {
            sum += Integer.parseInt(result.getValue()+"") ;
        }
        return sum;
    }
}
