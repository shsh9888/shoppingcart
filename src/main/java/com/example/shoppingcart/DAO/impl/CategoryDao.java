package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.CategoryDAOInterface;
import com.example.shoppingcart.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDao implements CategoryDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Boolean addCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(category);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteCategory(String categoryId) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(session.find(Category.class, categoryId));
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
