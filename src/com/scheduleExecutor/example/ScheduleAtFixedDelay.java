package com.scheduleExecutor.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedDelay {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        //schedule a task at 1 second interval
        int initialDelay = 0;
        int period = 1;
        executor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
        //here we are using scheduleAtFixedDelay so after completing each tasks executor
        // will delay one second and then start next task. So, my thread pool will not go out of capacity
    }
}
