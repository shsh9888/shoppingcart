package com.example.shoppingcart;

import com.example.shoppingcart.DAO.impl.UserDao;
import com.example.shoppingcart.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
@EnableAutoConfiguration
public class ShoppingcartApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);

    }

}
