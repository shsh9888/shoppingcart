package com.example.shoppingcart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/login", method =  RequestMethod.GET)
    public void userLogin(){

        System.out.println("");
    }
    @RequestMapping(value = "/register", method =  RequestMethod.GET)
    public void userRegister(){
        System.out.println("heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy11111111111111111");



    }
}
