package com.example.shoppingcart.logger;

public class DebugLogger implements Logger{


    @Override
    public void log(String message) {
        System.out.println("DEbug: " + message);
    }
}
