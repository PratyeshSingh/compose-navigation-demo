package com.example.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SolutionInJava {

    public static void main(String[] args) {
        System.out.println("Hello");




//        printReverseNumber();
//        performThreadPool();

    }







    static void printReverseNumber() {

        int num = 1234, reversed = 0;

        System.out.println("Original Number: " + num);

        // run loop until num becomes 0
        while(num != 0) {

            // get last digit from num
            int digit = num % 10;
            reversed = reversed * 10 + digit;

            System.out.println("EX Reversed Number: " + reversed);

            // remove the last digit from num
            num /= 10;
        }

        System.out.println("Reversed Number: " + reversed);
    }

    static void performThreadPool(){

        ExecutorService service = Executors.newFixedThreadPool(10);
////         for download image
//        List<String> images = new ArrayList<>();
//        for (String image : images) {
//            service.execute(new IOTask(image));
//        }

//        service.execute(new IOTask());
        service.execute(new NetworkTask());

        service.shutdown();

    }
}

