package com.example.shoppingcart.logger;

/**
 * Implementation of logger with type Error
 */
public class InfoLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Info: " + message);


    }
}
