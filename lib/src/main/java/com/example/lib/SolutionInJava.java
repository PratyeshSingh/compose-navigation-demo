package com.example.lib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SolutionInJava {

    public static void main(String[] args) {
        System.out.println("Hello");

        ExecutorService service = Executors.newFixedThreadPool(10);
        // for
        service.execute(new IOTask());
        service.execute(new NetworkTask());

    }
}
class NetworkTask implements Runnable{

    @Override
    public void run() {
        // do something
    }
}

class IOTask implements Runnable{

    @Override
    public void run() {
        // do something
    }
}