package com.synchronizedWithExecutor.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

public class ReadWriteLockExample {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(10);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
            }
        });
        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            }  catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }
}
