package com.atomic.example;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntergerExample {
    AtomicInteger atomicInt = new AtomicInteger(0);

    public void increment() {
        //instead of synchronize we are using atomic integer
        // which is atomic in nature and thread safe no no lock or synchronize is required.

        atomicInt.incrementAndGet();
        }

    public int getValue() {
        return atomicInt.get();
    }
}
