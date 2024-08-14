package com.waitNotifyNotifyAll.example;

import java.util.Queue;
import java.util.Random;

public class Consumer implements Runnable{
    private Queue<Integer> sharedQueue;
    int sharedQueueSize;

    public Consumer(Queue<Integer> sharedQueue, int sharedQueueSize) {
        this.sharedQueue = sharedQueue;
        this.sharedQueueSize = sharedQueueSize;
    }

    @Override
    public void run() {
        //this while to ensure it is consuming continuously
        while(true){
            synchronized (sharedQueue){
                //do not wait on if condition. always wait on while
                //in case of if condition it can happen that producer has notified before
                //consumer started waiting. So it will wait forever
                while(sharedQueue.isEmpty()){
                    try {
                        System.out .println("Queue is empty, "
                                + "Consumer thread "+Thread.currentThread().getName()+" is waiting for "
                                + "Producer to produce something");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Consumer thread "+Thread.currentThread().getName()+" is Consuming value : " + sharedQueue.remove());
                //in case of notify only it will notify only one thread out
                //of all threads waiting. which thread that gets the notification
                //is not in our control. Scheduler chooses that thread.
                //sharedQueue.notify();
                //for notifyAll() all the threads waiting will be notified.
                //but only one thread will be able to get the lock.
                sharedQueue.notifyAll();
            }
        }
    }
}
