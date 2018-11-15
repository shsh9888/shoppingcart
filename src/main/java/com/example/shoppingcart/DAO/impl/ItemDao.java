package com.example.shoppingcart.DAO.impl;

import com.example.shoppingcart.DAO.ItemDaoInterface;
import com.example.shoppingcart.entities.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ItemDao implements ItemDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ArrayList<Item> getAllItems() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select i from " + Item.class + " i ";
        Query<Item> query = session.createQuery(sql);
        return (ArrayList<Item>) query.getResultList();
    }

    @Override
    public ArrayList<Item> getAllItemsAddedByUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select i from " + Item.class + " i where i.owner=:username";
        Query<Item> query = session.createQuery(sql);
        query.setParameter("username",username);
        return (ArrayList<Item>) query.getResultList();
    }

    @Override
    public ArrayList<Item> searchByName(String itemName) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "Select i from " + Item.class + " i where i.item_name like :itemname";
        Query<Item> query = session.createQuery(sql);
        query.setParameter("itemname",itemName);
        return (ArrayList<Item>) query.getResultList();    }
;

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
