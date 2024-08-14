package com.extendThread.example;

public class Main {
    public static void main(String[] args){
        System.out.println("Current thread"+ Thread.currentThread().getName());
        MyThread myThread = new MyThread();
        //to start running task in new callstack we need to start method
        myThread.start();
        MyThread myThread2 = new MyThread();
        //If we call run directly it doesn't run in new call stack
        myThread2.run();
        //we can call overloaded run method, but it doesn't run in separate callstack
        myThread2.run("Sambit");
    }
}
