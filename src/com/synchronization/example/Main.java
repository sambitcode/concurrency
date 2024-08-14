package com.synchronization.example;

public class Main {
    public static void main(String[] args){
        Calculator cal = new Calculator();
        Thread student1 = new Thread(()->cal.getSumOfArray(new int[]{10,20,30}));
        Thread student2 = new Thread(()->cal.getSumOfArray(new int[]{1,2,3}));

        student1.setName("student-1 thread");
        student2.setName("student-2 thread");
        student1.start();
        student2.start();
    }
}
