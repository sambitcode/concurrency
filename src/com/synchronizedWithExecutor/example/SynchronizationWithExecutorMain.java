package com.synchronizedWithExecutor.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizationWithExecutorMain {
    public static void main(String[] args) {
        NonSyncCounter nonSyncCounter = new NonSyncCounter();
        // you will see that increment number is different during different run.
        // This is because increment variable is used by two threads in WithoutSynchronized class simultaneously
        ExecutorService executorFirst = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10000; i++) {
            executorFirst.submit(nonSyncCounter::increment);
        }
        executorFirst.shutdown();
        try {
            executorFirst.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        System.out.println(nonSyncCounter.getValue());
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        // you will see that increment number is always 10000 different run.
        // This is because increment variable is updated inside synchronized block
        ExecutorService executorSecond = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10000; i++) {
            executorSecond.submit(synchronizedCounter::syncIncrement);
        }
        executorSecond.shutdown();
        try {
            executorSecond.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        System.out.println(synchronizedCounter.getValue());

        LockCounter lockCounter = new LockCounter();
        // you will see that increment number is always 10000 different run.
        // This is because increment variable is updated using lock
        ExecutorService executorThird = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10000; i++) {
            executorThird.submit(lockCounter::lockIncrement);
        }
        executorThird.shutdown();
        try {
            executorThird.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        System.out.println(lockCounter.getValue());
    }
}
