package com.example.lib;


public class EvenOddPrinter {
    // print with 2-thread one oddd and other even number only

    int counter = 1;
    int SIZE = 0;

    EvenOddPrinter(int provideNumber) {
        SIZE = provideNumber;
    }

    void printEven() {
        synchronized (this) {
            while (counter < SIZE) {


                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(counter);
                counter++;
                notify();


            }
        }
    }

    void printOdd() {
        synchronized (this) {
            while (counter < SIZE) {


                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(counter);
                counter++;
                notify();



            }
        }
    }

    void performPrinting() {
        Thread even = new Thread(this::printEven);
        Thread odd = new Thread(this::printOdd);

        odd.start();
        even.start();

    }

    public static void main(String[] args) {
        new EvenOddPrinter(10).performPrinting();
    }

}