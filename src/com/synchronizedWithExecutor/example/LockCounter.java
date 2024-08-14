package com.synchronizedWithExecutor.example;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    int count = 0;
    ReentrantLock lock = new ReentrantLock();
    /*synchronized */public void lockIncrement() {
        //instead of synchronize we will use lock..
        //it works same as synchronize but give us fine grain control
        //synchronized (this) {
        lock.lock();
        try {
            count = count + 1;
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        return count;
    }
}
