package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.ItemDaoInterface;
import com.example.shoppingcart.entities.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Repository
@Service
@ComponentScan

/**
 * DAO  pattern:  Implements  all the db operations to be done with resepct to Item table
 */
public class ItemDao implements ItemDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get an item based on its id
     * @param id
     * @return
     */
    @Override
    public Item getItem(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Item.class, id);
    }

    /**
     * Get all the items in the store
     * @return
     */
    @Override
    public ArrayList<Item> getAllItems() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Item i";
        Query query = session.createQuery(sql);
        return (ArrayList<Item>) query.getResultList();
    }

    /**
     * Get all the items which are added by particular seller
     * @param username
     * @return
     */
    @Override
    public ArrayList<Item> getAllItemsAddedByUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select i from " + Item.class + " i where i.owner=:username";
        Query<Item> query = session.createQuery(sql);
        query.setParameter("username",username);
        return (ArrayList<Item>) query.getResultList();
    }


    /**
     * Search for an item based on its name
     * @param itemName
     * @return
     */
    @Override
    public ArrayList<Item> searchByName(String itemName) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Item i where i.item_name like :itemname";
        Query<Item> query = session.createQuery(sql);
        query.setParameter("itemname","%" +itemName +"%");
        return (ArrayList<Item>) query.getResultList();
    }

    /**
     * Add and item to the database.
     * @param item
     * @return
     */
    @Override
    public Boolean addItem(Item item) {
        Session session = sessionFactory.getCurrentSession();
        Item it = new Item();
        it.setItem_category(item.getItem_category());
        it.setItem_name(item.getItem_name());
        it.setOwner(item.getOwner());
        it.setPrice(item.getPrice());
        try {
            session.save(it);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Delete an item from the db
     * @param itemId
     * @return
     */
    @Override
    public Boolean deleteItem(String itemId) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(session.find(Item.class, itemId));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
