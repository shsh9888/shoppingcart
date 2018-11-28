package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Order;
import com.example.shoppingcart.entities.User;

import java.util.ArrayList;
/**
 * Interface exposes all the db operations to be done with resepct to Order table
 */
public interface OrderDaoInterface {

    Boolean addOrder(Order order);

    ArrayList<Order> getOrdersByUser(User username);
    ArrayList<Order> getAllOrders();


    Boolean checkoutOrder(String orderId);




}
