package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDao implements UserDaoInterface {

//    @Autowired
    private  SessionFactory sessionFactory;

    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();

        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void DeleteUser(User user) {

    }
}
