package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.User;

public interface UserDaoInterface {
    User getUser(String username);
    void saveUser(User user);
    void updateUser(User user);
    void DeleteUser(User user);
}
