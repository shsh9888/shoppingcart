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
/**
 * DAO pattern : pattern Implements  all the db operations to be done with resepct to User table
 */
public class UserDao implements UserDaoInterface {


    @Autowired
    private  SessionFactory sessionFactory;

    /**
     * Returns the User object for given username
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.find(User.class, username);

    }

    /**
     * Saves the given user with properties
     * @param user
     * @return
     */
    @Override
    public Boolean saveUser(User user) {
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

    /**
     * update the user for given properties
     * @param user
     */
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

    /**
     * Delete the user given the user object
     * @param user
     */
    @Override
    public void DeleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    /**
     * Given an username return the user for it.
     * @param username
     * @return
     */
    @Override
    public User loadUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(User.class, username);
    }
}
