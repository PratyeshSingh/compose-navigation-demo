package com.example.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SolutionInJava {

    public static void main(String[] args) {
        System.out.println("Hello");

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
