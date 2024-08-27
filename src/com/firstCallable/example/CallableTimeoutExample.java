package com.firstCallable.example;

import java.util.concurrent.*;

public class CallableTimeoutExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        try {
            //here we are passing callable which returns a future object 123
            Future<Integer> future = executor.submit(()-> {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            });
            System.out.println("future done? "+future.isDone());
            System.out.println("future done? "+future.isDone());
            System.out.println("future done? "+future.isDone());
            Integer result = future.get(1, TimeUnit.SECONDS);

            System.out.println("future done? " + future.isDone());
            System.out.println("result: " + result);
            //executors doesn't shut down automatically. we need to explicitly call shutdownNow or shutdown
            //shut down method waits for assigned task to get completed then shuts down executor
            executor.shutdown();
            //Blocks until all tasks have completed execution after a shutdown request,
            // or the timeout occurs, or the current thread is interrupted, whichever happens first.
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
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
