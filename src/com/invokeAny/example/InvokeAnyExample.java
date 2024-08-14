package com.invokeAny.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();
        try {
            //creating a list of callables
            List<Callable<String>> callableList = Arrays.asList(
                    callable("task1",10),
                    callable("task2",1),
                    callable("task3",12)
            );
            //invoke any will send back result of the task which completed first
            String future = executor.invokeAny(callableList);
            System.out.println(future);

            //executors doesn't shut down automatically. we need to explicitly call shutdownNow or shutdown
            //shut down method waits for assigned task to get completed then shuts down executor
            executor.shutdown();
            //Blocks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("tasks interrupted");
        } finally {
            if(!executor.isTerminated()){
                System.out.println("cancel non finished tasks and shut down immediately");
            }
            executor.shutdownNow();
            System.out.println("shut down finished");
        }
    }
    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
