package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.OrderDaoInterface;
import com.example.shoppingcart.entities.Item;
import com.example.shoppingcart.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderDao implements OrderDaoInterface {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Boolean addOrder(Order order) {
        Session session = sessionFactory.openSession().getSession();
        Order newOrder = new Order();
        newOrder.setItems((ArrayList<Item>) order.getItems());
        newOrder.setOwner(order.getOwner());
        newOrder.setStatus(order.getStatus());
        newOrder.setTotal_price(order.getTotal_price());
        try {
            session.save(newOrder);
            return true;

        }catch (Exception e){
            return  false;
        }


    }

    @Override
    public ArrayList<Order> getOrdersByUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select O from " + Order.class + " O where O.owner=:owner";
        Query<Order> query = session.createQuery(sql);
        query.setParameter("owner", username);
        return  (ArrayList<Order>) query.getResultList();

    }
    //change this to general update later
    @Override
    public Boolean checkoutOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.find(Order.class, orderId);
        order.setStatus("checkedout");
        session.update(order);
        return true;
    }
}
