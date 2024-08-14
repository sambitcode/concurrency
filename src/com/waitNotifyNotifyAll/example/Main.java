package com.waitNotifyNotifyAll.example;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Queue<Integer> sharedQueue = new LinkedList<>();
        Thread prod1 = new Producer(sharedQueue,10);
        Thread prod2 = new Thread(new Producer(sharedQueue,10));
        Thread prod3 = new Thread(new Producer(sharedQueue,10));
        Thread prod4 = new Thread(new Producer(sharedQueue,10));
        Thread consumer1 = new Thread(new Consumer(sharedQueue,10));
        prod1.setName("producer-1");
        prod2.setName("producer-2");
        prod3.setName("producer-3");
        prod4.setName("producer-4");
        consumer1.setName("consumer-1");

        prod1.start();
        prod2.start();
        prod3.start();
        prod4.start();
        consumer1.start();
    }
}
