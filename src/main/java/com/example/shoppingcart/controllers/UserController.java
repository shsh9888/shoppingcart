package com.example.shoppingcart.controllers;

import com.example.shoppingcart.DAO.impl.CategoryDao;
import com.example.shoppingcart.DAO.impl.ItemDao;
import com.example.shoppingcart.DAO.impl.OrderDao;
import com.example.shoppingcart.DAO.impl.UserDao;
import com.example.shoppingcart.entities.Category;
import com.example.shoppingcart.entities.Item;
import com.example.shoppingcart.entities.Order;
import com.example.shoppingcart.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ItemDao itemDao;


    @Autowired
    OrderDao orderDao;

    private User loggedInUser;

    private ArrayList<Item> cartItems =new ArrayList<>();

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
//        model.addAttribute("user", "Shravan");
        return "login";
    }
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        loggedInUser =null;
        return "login";
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(HttpServletRequest request, Model model) {
        return "register";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.GET)
    public String category(HttpServletRequest request, Model model) {
        model.addAttribute("user", loggedInUser);
        return "category";
    }

    @RequestMapping(value = {"/createItemPage"}, method = RequestMethod.GET)
    public String createItem(HttpServletRequest request, Model model) {
        model.addAttribute("categoryList", categoryDao.getAll());
        model.addAttribute("user", loggedInUser);
        return "createItemPage";
    }

    @RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
    public String getProducts(HttpServletRequest request, Model model) {
        model.addAttribute("items", itemDao.getAllItems());
        model.addAttribute("user", loggedInUser);

        return "productList";
    }


    @RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model) {
        model.addAttribute("items", cartItems);
        model.addAttribute("user", loggedInUser);
        return "checkout";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(HttpServletRequest request,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "role") String role,
                           Model model) {

        User user = new User(userName, name, password, email, role);
        if (userDao.saveUser(user)) {
            model.addAttribute("user", user);
            return "login";
        }
        model.addAttribute("error", "There was some issue Please try again");
        return "register";
    }

    @RequestMapping(value = {"/checkLogin"}, method = RequestMethod.POST)
    public String checklogin(HttpServletRequest request, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, Model model) {

        User user = userDao.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            loggedInUser = user;
            return "home";
        }
        model.addAttribute("error", "Please enter the right credentials");
        return "login";
    }

    @RequestMapping(value = {"/addCategory"}, method = RequestMethod.POST)
    public String addCategory(HttpServletRequest request,
                              @RequestParam(value = "name") String name,
                              Model model) {

        Category cat = new Category(name, loggedInUser);
        if (categoryDao.addCategory(cat)) {
            model.addAttribute("user", loggedInUser);
            return "home";
        }
        model.addAttribute("error", "There was some issue Please try again");
        return "category";
    }

    @RequestMapping(value = {"/createItem"}, method = RequestMethod.POST)
    public String createItem(HttpServletRequest request,
                              @RequestParam(value = "name") String name,
                             @RequestParam(value = "price") Float price,
                             @RequestParam(value = "category_name") String category,
                             Model model) {

        Category cat = categoryDao.getCategory(category);
        Item item = new Item(name,price,loggedInUser,cat);
        if (itemDao.addItem(item)) {
            model.addAttribute("user", loggedInUser);
            return "home";
        }
        model.addAttribute("error", "There was some issue Please try again");
        return "createItemPage";
    }


    @RequestMapping(value = {"/placeOrder"}, method = RequestMethod.POST)
    public String placeOrder(HttpServletRequest request,
                             @RequestParam(value = "payment") String paymentMethod,
                             Model model) {

       if(orderDao.addOrder(new Order(loggedInUser, cartItems, "Placed"))) {
           model.addAttribute("user", loggedInUser);
           return "home";
        }
        model.addAttribute("items", cartItems);
        model.addAttribute("user", loggedInUser);
        return "checkout";
    }

    @RequestMapping(value = {"/buyProduct"}, method = RequestMethod.GET)
    public String buyProduct(HttpServletRequest request,
                             @RequestParam(value = "id") String itemId,
                             Model model) {
        Item i = itemDao.getItem(itemId);
        cartItems.add(i);
        model.addAttribute("items", itemDao.getAllItems());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("message", "Item " + i.getItem_name() + " has been added to your cart");

        return "productList";
    }

}
