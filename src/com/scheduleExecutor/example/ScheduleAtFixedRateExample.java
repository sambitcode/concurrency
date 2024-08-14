package com.scheduleExecutor.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        //schedule a task at 1 second interval
        int initialDelay = 0;
        int period = 1;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        //but the problem is if my task takes more than one sec to complete
        // then soon I will have more task than the threads available
        // as executor is starting every task just after one second where as my tasks are taking two seconds to complete
    }
}
