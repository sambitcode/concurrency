package com.implementRunnable.example;

public class Java8DirectRunnable {
    public static void main(String[] args) {
        System.out.println("Running thread name "+ Thread.currentThread().getName());
        //We can directly create Runnable object
        Runnable myRunnable = () -> System.out.println("Running thread name "+ Thread.currentThread().getName());
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
}
