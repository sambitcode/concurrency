package com.firstExecutorService.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FirstExecutorService {
    //Working with thread is error-prone
    //Concurrency api introduces the concept of ExecutorService
    //Executors are capable of managing thread pools,
    // so we don't need to manually create threads and run tasks in an asynchronous fashion

    public static void main(String[] args){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            executor.submit(()-> System.out.println("first executor service"));
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
