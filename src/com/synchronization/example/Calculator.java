package com.synchronization.example;

public class Calculator {

    //remove synchronize keyword in method signature or block
    //we will see absurd output as both thread is modifying value of sum simultaneously
    public /*synchronized*/ void getSumOfArray(int[] arr) {
        synchronized (this) {
            int sum = 0;
            for (int num : arr) {
                System.out.println(Thread.currentThread().getName()
                        + " adds " + sum + " to " + num + " and get -> ");
                sum = sum + num;
                System.out.println(sum);
                //adding some delay to make sure multiple thread access it in same time
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
