package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Item;

import java.util.ArrayList;

public interface ItemDaoInterface {
    ArrayList<Item> getAllItems();
    ArrayList<Item> getAllItemsAddedByUser(String username);
    ArrayList<Item> searchByName(String itemName);

    Boolean addItem(Item item);

    Boolean deleteItem(String itemId);




}
