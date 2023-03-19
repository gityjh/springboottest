package com.example.springboot.demo.syntest;

public class MyObject {
    private String userName;
    private String password;
    public synchronized void setValue(String userName,String password){
        this.userName = userName;
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue userName:"+userName+",password:"+password);
    }
    public synchronized void getValue(){
        System.out.println("getValue userName:"+userName+",password:"+password);
    }
    public static void main(String[] args){
        final MyObject myObject = new MyObject();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                myObject.setValue("www","123");
            }
        });
        t.start();
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        myObject.getValue();
    }
}
