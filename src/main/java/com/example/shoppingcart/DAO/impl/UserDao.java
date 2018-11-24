package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.UserDaoInterface;
import com.example.shoppingcart.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Transactional
@Repository
@Service
@ComponentScan
public class UserDao implements UserDaoInterface {


    @Autowired
    private  SessionFactory sessionFactory;

    @Override
    public User getUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.find(User.class, username);

    }

    @Override
    public Boolean saveUser(User user) {
        System.out.println("sssssssssssssssssssssssssssssssssssss"+sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        User us = new User();
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setName(user.getName());
        us.setRole(user.getRole());
        us.setUsername(user.getUsername());
        try {
            session.save(us);
            }catch (Exception e){
            return false;
        }

        return true;
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

    @Override
    public User loadUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(User.class, username);
    }
}
