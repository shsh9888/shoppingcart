package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Order;
import com.example.shoppingcart.entities.User;

import java.util.ArrayList;

public interface OrderDaoInterface {

    Boolean addOrder(Order order);

    ArrayList<Order> getOrdersByUser(String username);

    Boolean checkoutOrder(String orderId);


}
