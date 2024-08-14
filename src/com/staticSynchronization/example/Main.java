package com.staticSynchronization.example;

public class Main {
    public static void main(String[] args){
        Thread printer1 = new Thread(Printer::printFivePages);
        Thread printer2 = new Thread(Printer::printFivePages);
        Thread printer3 = new Thread(Printer::printFivePages);

        printer1.setName("printer-1");
        printer2.setName("printer-2");
        printer3.setName("printer-3");
        printer1.start();
        printer2.start();
        printer3.start();
    }
}
