package com.waitNotifyNotifyAll.example;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread{
    private Queue<Integer> sharedQueue;
    int sharedQueueSize;

    public Producer(Queue<Integer> sharedQueue, int sharedQueueSize) {
        this.sharedQueue = sharedQueue;
        this.sharedQueueSize = sharedQueueSize;
    }

    @Override
    public void run() {
        //this while to ensure it is producing continuously
        while(true){
            synchronized (sharedQueue){
                //do not wait on if condition. always wait on while
                //in case of if condition it can happen consumer has notified before
                //producer started waiting. So it will wait forever
                while(sharedQueue.size()==10){
                    try {
                        System.out .println("Queue is full, "
                                + "Producer thread "+Thread.currentThread().getName()+" is waiting for "
                                + "consumer to take something from queue");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Producer thread "+Thread.currentThread().getName()+" is Producing value : " + i);
                sharedQueue.add(i);
                //in case of notify only it will notify only one thread out
                //of all threads waiting. which thread that gets the notification
                //is not in our control. Scheduler chooses that thread.
                //sharedQueue.notify();
                //for notifyAll() all the threads waiting will be notified.
                //but only one thread will be able to get the lock.
                //as we get lock on object thus wait, notify, notifyAll() is part of object class
                sharedQueue.notifyAll();
            }
        }
    }
}
