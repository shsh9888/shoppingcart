package com.example.shoppingcart;

import com.example.shoppingcart.DAO.impl.UserDao;
import com.example.shoppingcart.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShoppingcartApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);
        run();

    }


    public static void run() {

//        UserDao userService = new UserDao();
//        User user  = new User();
//        user.setRole("admin");
//        user.setName("Shravan");
//        user.setEmail("ss");
//        user.setUsername("shsh");
//        user.setPassword("shsh");
//        userService.saveUser(user);
//        System.out.println(userService.getUser("shsh").getEmail());
    }


}
