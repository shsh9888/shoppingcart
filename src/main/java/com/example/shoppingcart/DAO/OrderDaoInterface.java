package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Order;
import com.example.shoppingcart.entities.User;

import java.util.ArrayList;

public interface OrderDaoInterface {

    void addOrder(Order order);
    ArrayList<Order> getOrdersByUser(String username);

}
