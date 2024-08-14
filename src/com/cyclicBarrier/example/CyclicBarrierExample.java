package com.cyclicBarrier.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


//In this example, we have a main thread that creates three other threads (t1, t2, and t3) and starts them.
// We also have a CyclicBarrier initialized with a count of 3.
// Each time one of the threads completes its work, it calls the await() method on the barrier.
// Once all three threads have reached the barrier, the count reaches zero, and all threads are released and can continue their execution.
public class CyclicBarrierExample {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            // do some work
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            // continue execution
        });

        Thread t2 = new Thread(() -> {
            // do some work
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            // continue execution
        });

        Thread t3 = new Thread(() -> {
            // do some work
            try {
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            // continue execution
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
