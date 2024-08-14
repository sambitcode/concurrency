package com.implementRunnable.example;

public class MultipleThreadExample {
    public static void main(String[] args){
        //as runnable is functional interface we can pass lamda in it.
        Runnable myRunnable = () -> {
            for(int i =0; i<3; i++){
                System.out.println("Run by "+Thread.currentThread().getName() + " "+ i);
            }
        };
        Thread t1 = new Thread(myRunnable);
        t1.setName("sambit");
        Thread t2 = new Thread(myRunnable);
        t2.setName("sankha");
        Thread t3 = new Thread(myRunnable);
        t3.setName("pallavi");

        t1.start();
        t2.start();
        t3.start();
    }
}
