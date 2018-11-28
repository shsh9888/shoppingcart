package com.example.shoppingcart.payment;

/**
 * An implementation of the Payment overriding the processpayment
 * This takes care of all the payment comes through visa
 */
public class Visa implements Payment {
    private  String CustomerName;
    private String cardnumber;


    @Override
    public boolean processPayment(String orderId, float totalAmount) {
        System.out.println("PAYMENT METHOD : VISA!!!");
        System.out.println("Payment Successful for order :" + orderId + ". Paid " + totalAmount);
        return true;
    }
}
