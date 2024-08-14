package com.invokeAll.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        try {
           //creating a list of callables
            List<Callable<String>> callableList = Arrays.asList(
                    () -> "task1",
                    () -> "task2",
                    () -> "task3"
            );
            List<Future<String>> future = executor.invokeAll(callableList);
            future.forEach(fu -> {
                try {
                    System.out.println(fu.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
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
