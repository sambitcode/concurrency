package com.synchronizedWithExecutor.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class FineGrainedLockExample {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }
}
