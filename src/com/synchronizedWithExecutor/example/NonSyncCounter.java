package com.synchronizedWithExecutor.example;

public class NonSyncCounter {
    int count = 0;

    public void increment() {
        count = count + 1;
    }

    public int getValue() {
        return count;
    }
}
