package com.countdownlatch.example;

import java.util.concurrent.CountDownLatch;



//In this example, we have a main thread that creates three other threads (t1, t2, and t3) and starts them.
// We also have a CountDownLatch initialized with a count of 3. Each time one of the threads completes its work,
// it calls the countDown() method on the latch,
// which decrements the count by one.
// Once the count reaches zero, the main thread is released and can continue its execution.
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        // do some work
        Thread t1 = new Thread(latch::countDown);

        // do some work
        Thread t2 = new Thread(latch::countDown);

        // do some work
        Thread t3 = new Thread(latch::countDown);

        t1.start();
        t2.start();
        t3.start();

        // wait for t1, t2, and t3 to complete
        latch.await();

        // continue execution
    }
}
