package com.extendThread.example;

//extending thread
public class MyThread extends Thread{
    //override run() method
    public void run(){
        //we will write the portion of code here which we want to run in thread
        System.out.println("Current thread"+ Thread.currentThread().getName());
        System.out.println("very important task running in thread");
    }
    //overloaded run method
    public void run(String s){
        //we will write the portion of code here which we want to run in thread
        System.out.println("Current thread "+ Thread.currentThread().getName() +" "+ s);
        System.out.println("very important task running in thread");
    }
}
