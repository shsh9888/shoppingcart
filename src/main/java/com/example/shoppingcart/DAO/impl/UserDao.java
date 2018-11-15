package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.UserDaoInterface;
import com.example.shoppingcart.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDao implements UserDaoInterface {


    @Autowired
    private  SessionFactory sessionFactory;

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(User.class, username);

    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User us = new User();
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setName(user.getName());
        us.setRole(user.getRole());
        us.setUsername(user.getUsername());
        session.save(us);
    }


    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User us = new User();
        us.setUsername(user.getUsername());
        us.setPassword(user.getPassword());
        us.setEmail(user.getEmail());
        us.setName(user.getName());
        us.setRole(user.getRole());
        session.update(us);
    }

    @Override
    public void DeleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
