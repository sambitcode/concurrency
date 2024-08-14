package com.implementRunnable.example;


//implement runnable
public class MyRunnable implements Runnable{
    @Override
    public void run(){
        //we will write the portion of code here which we want to run in thread
        System.out.println("Current thread"+ Thread.currentThread().getName());
        System.out.println("very important task running in thread");
    }
}
