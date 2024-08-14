package com.scheduleExecutor.example;

import java.util.concurrent.*;

public class ScheduleExecutorBasic {
    public static void main(String[] args) {
        ScheduledExecutorService  executor = Executors.newScheduledThreadPool(1);
        try {

            Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
            //schedule a task after 3 seconds delay
            ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

            TimeUnit.MILLISECONDS.sleep(1337);

            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
            System.out.printf("Remaining Delay: %sms", remainingDelay);
            //executors doesn't shut down automatically. we need to explicitly call shutdownNow or shutdown
            //shut down method waits for assigned task to get completed then shuts down executor
            executor.shutdown();
            //Blocks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if(!executor.isTerminated()){
                System.out.println("cancel non finished tasks and shut down immediately");
            }
            executor.shutdownNow();
            System.out.println("shut down finished");
        }
    }
}
