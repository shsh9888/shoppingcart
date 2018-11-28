package com.example.shoppingcart.logger;

/**
 * Implementation of logger with type Debug
 */
public class DebugLogger implements Logger{


    @Override
    public void log(String message) {
        System.out.println("DEbug: " + message);
    }
}
