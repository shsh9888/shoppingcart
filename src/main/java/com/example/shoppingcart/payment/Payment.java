package com.example.shoppingcart.payment;

/**
 * Payment interface exposing the processpayment .
 * This is part of the strategy design pattern
 */
public interface Payment {

    public boolean processPayment(String orderId, float totalAmount);
}
