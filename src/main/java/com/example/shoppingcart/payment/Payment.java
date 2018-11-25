package com.example.shoppingcart.payment;

public interface Payment {

    public boolean processPayment(String orderId, float totalAmount);
}
