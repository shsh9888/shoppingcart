package com.example.shoppingcart.payment;

public class MasterCard implements Payment  {
    private  String CustomerName;
    private String cardnumber;


    @Override
    public boolean processPayment(String orderId, float totalAmount) {
        System.out.println("PAYMENT METHOD : Mastercard!!!");
        System.out.println("Payment Successful for order :" + orderId + ". Paid " + totalAmount);
        return true;
    }
}
