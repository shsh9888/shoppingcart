package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.CategoryDAOInterface;
import com.example.shoppingcart.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Repository
@Service
@ComponentScan
/**
 * DAO  pattern:  Implements  all the db operations to be done with resepct to Category
 */
public class CategoryDao implements CategoryDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Add a category to db
     * @param category
     * @return
     */
    @Override
    public Boolean addCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(category);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete a category from the table
     * @param categoryId
     * @return
     */
    @Override
    public Boolean deleteCategory(String categoryId) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(session.find(Category.class, categoryId));
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Get a category based on the name
     * @param categoryName
     * @return
     */
    @Override
    public Category getCategory(String categoryName) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.find(Category.class, categoryName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Get all the categories in the table
     * @return
     */
    @Override
    public ArrayList<Category> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query q =session.createQuery("from Category c");
        return (ArrayList<Category>) q.list();
    }


}
