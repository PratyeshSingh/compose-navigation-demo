package com.example.lib;

public class NetworkTask implements Runnable {

    @Override
    public void run() {
        // do something
    }
}

class IOTask implements Runnable {

    private String imageUrl = "";

    IOTask(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        // do something
    }
}