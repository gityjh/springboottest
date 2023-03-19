package com.example.springboot.demo.syntest;

public class SynVolatileTest extends  Thread {
    private volatile boolean flag = true;
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    @Override
    public void run() {
        while (flag){

        }
        System.out.println("线程结束");
    }
    public static void main(String[] args){
        try{
            SynVolatileTest synVolatileTest = new SynVolatileTest();
            synVolatileTest.start();
            Thread.sleep(2000);
            synVolatileTest.setFlag(false);
            Thread.sleep(1000);
            System.out.println(synVolatileTest.flag);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
