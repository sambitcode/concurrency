package com.staticSynchronization.example;

public class Printer {
    //remove synchronize keyword in method signature or block
    //we will see absurd output as both thread is modifying value of count simultaneously
    //There is only one copy of the static
    //data you’re trying to protect, so you only need one lock per class to
    //synchronize static methods or a lock for the whole class. There is such a
    //lock; every class loaded in Java has a corresponding instance of
    //java.lang.Class representing that class. It’s that java.lang.Class instance
    //whose lock is used to protect any synchronized static methods of the class.
    public static /*synchronized*/ void printFivePages(){
        synchronized (Printer.class) {
            for (int count = 1; count <= 5; count++) {
                System.out.println(Thread.currentThread().getName() + " is printing Printing page :" + count);
            }
        }
    }
}
