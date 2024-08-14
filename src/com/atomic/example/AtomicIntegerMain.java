package com.atomic.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicIntegerMain {
    public static void main(String[] args) {
        AtomicIntergerExample atomicIntergerExample = new AtomicIntergerExample();
        // you will see that increment number is same 10000 during different run.
        // This is because increment variable is atomic int as it is thread safe it is able to maintain its value

        ExecutorService executorFirst = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10000; i++) {
            executorFirst.submit(atomicIntergerExample::increment);
        }
        executorFirst.shutdown();
        try {
            executorFirst.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        System.out.println(atomicIntergerExample.getValue());
    }
}
