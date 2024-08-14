package com.synchronizedWithExecutor.example;

public class SynchronizedCounter {
    int count = 0;

    /*synchronized */public void syncIncrement() {
        synchronized (this) {
            count = count + 1;
        }
    }

    public int getValue() {
        return count;
    }
}
