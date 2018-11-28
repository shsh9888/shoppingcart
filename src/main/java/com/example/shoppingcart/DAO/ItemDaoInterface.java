package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Item;

import java.util.ArrayList;
/**
 * Interface exposes all the db operations to be done with resepct to Item table
 */
public interface ItemDaoInterface {
    Item getItem(String id);
    ArrayList<Item> getAllItems();
    ArrayList<Item> getAllItemsAddedByUser(String username);
    ArrayList<Item> searchByName(String itemName);

    Boolean addItem(Item item);

    Boolean deleteItem(String itemId);




}
