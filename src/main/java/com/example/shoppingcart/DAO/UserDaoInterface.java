package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserDaoInterface {
    User loadUserByUsername(String username);
    User getUser(String username);
    Boolean saveUser(User user);
    void updateUser(User user);
    void DeleteUser(User user);
}
