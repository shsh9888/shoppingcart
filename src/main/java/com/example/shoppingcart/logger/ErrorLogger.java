package com.example.shoppingcart.logger;

public class ErrorLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Info: " + message);

    }
}
