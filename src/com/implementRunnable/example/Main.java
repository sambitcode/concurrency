package com.implementRunnable.example;

public class Main {
    public static void main(String[] args){
        System.out.println("Current thread"+ Thread.currentThread().getName());
        //need to create thread object and then pass your runnable in it
        Thread myThread = new Thread(new MyRunnable());
        //again call start() not run()
        myThread.start();
    }
}
