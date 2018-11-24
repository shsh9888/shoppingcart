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

//    @Autowired
    private  static  UserDao userService = new UserDao();

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);
//        run();

    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }

        };
    }



    public  static void run() {

//        UserDao userService = new UserDao();
        User user  = new User();
        user.setRole("admin");
        user.setName("Shravan");
        user.setEmail("ss");
        user.setUsername("shsh");
        user.setPassword("shsh");
        System.out.println("Let's inspect the beans provided by Spring Boot:"+userService);

        userService.saveUser(user);

        System.out.println(userService.getUser("shsh").getEmail());
    }


}
