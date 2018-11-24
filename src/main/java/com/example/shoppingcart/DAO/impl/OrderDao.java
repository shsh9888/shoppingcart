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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
@Service
@ComponentScan

public class OrderDao implements OrderDaoInterface {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Boolean addOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(order);
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
