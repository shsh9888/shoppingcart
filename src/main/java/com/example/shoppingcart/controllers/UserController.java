package com.example.shoppingcart.controllers;

import com.example.shoppingcart.DAO.impl.CategoryDao;
import com.example.shoppingcart.DAO.impl.ItemDao;
import com.example.shoppingcart.DAO.impl.OrderDao;
import com.example.shoppingcart.DAO.impl.UserDao;
import com.example.shoppingcart.entities.Category;
import com.example.shoppingcart.entities.Item;
import com.example.shoppingcart.entities.Order;
import com.example.shoppingcart.entities.User;
import com.example.shoppingcart.logger.LoggerFactory;
import com.example.shoppingcart.payment.MasterCard;
import com.example.shoppingcart.payment.Payment;
import com.example.shoppingcart.payment.Visa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *  COntroller class to accept all the requests and call respective methods
 *  to handle the requests.
 *  This implements the frontcontroller pattern which exposes the APIs
 *  in the form of REST
 */
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

    private Payment pay;

//    private LoggerFactory loggerFactory = new LoggerFactory();

    private ArrayList<Item> cartItems = new ArrayList<>();

    /**
     * Looks for the login get call and returns the login page
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
//        model.addAttribute("user", "Shravan");
        return "login";
    }


    /**
     * Accepts the logout request and returns the login page
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model) {
        loggedInUser = null;
        return "login";
    }

    /**
         * Looks for the register get call and returns the register page
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(HttpServletRequest request, Model model) {
        return "register";
    }

    /**
     * Looks for the category call and returns the page to retun the category
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/category"}, method = RequestMethod.GET)
    public String category(HttpServletRequest request, Model model) {
        model.addAttribute("user", loggedInUser);
        return "category";
    }

    /**
     * Looks for the createItem get call and return a template where we can create items
     * Also sends categories as a part of this
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/createItemPage"}, method = RequestMethod.GET)
    public String createItem(HttpServletRequest request, Model model) {
        model.addAttribute("categoryList", categoryDao.getAll());
        model.addAttribute("user", loggedInUser);
        return "createItemPage";
    }

    /**
     * Looks for the call to products and lists all the products which can be bought
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/productList"}, method = RequestMethod.GET)
    public String getProducts(HttpServletRequest request, Model model) {
        model.addAttribute("items", itemDao.getAllItems());
        model.addAttribute("user", loggedInUser);

        return "productList";
    }

    /**
     * Looks for the checkout call and  returns the checkout page where user can select
     * the payment method
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model) {
        model.addAttribute("items", cartItems);
        model.addAttribute("user", loggedInUser);
        return "checkout";
    }

    /**
     * Looks for the register post calls and saves the user in the db
     * @param request
     * @param model
     * @return
     */
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

    /**
     * Checks the user credentials with the one in the db and validates return home and login
     * depending on the same
     * @param request
     * @param model
     * @return
     */
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

    /**
     * Handles the call for add catefory recieve s the category and adds the category to db,
     * @param request
     * @param model
     * @return
     */
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

    /**
     *  Handles the create item call from  seller and creates item
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/createItem"}, method = RequestMethod.POST)
    public String createItem(HttpServletRequest request,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "price") Float price,
                             @RequestParam(value = "category_name") String category,
                             Model model) {

        Category cat = categoryDao.getCategory(category);
        Item item = new Item(name, price, loggedInUser, cat);
        if (itemDao.addItem(item)) {
            model.addAttribute("user", loggedInUser);
            return "home";
        }
        model.addAttribute("error", "There was some issue Please try again");
        LoggerFactory.getLogger(LoggerFactory.DEBUG).log("Item creation failed please check the DB");

        return "createItemPage";
    }

    /**
     *  Handles the final order call with the proper payment method saves the order
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/placeOrder"}, method = RequestMethod.POST)
    public String placeOrder(HttpServletRequest request,
                             @RequestParam(value = "payment") String paymentMethod,
                             Model model) {
        Order order = new Order(loggedInUser, cartItems, "Placed");
        if (paymentMethod.equals("Visa")) {
            pay = new Visa();
        } else if (paymentMethod.equals("MasterCard")) {
            pay = new MasterCard();
        }
        if (pay.processPayment(order.getId(), order.getTotal_price()) && orderDao.addOrder(order)) {
            model.addAttribute("user", loggedInUser);
            return "home";
        }
        model.addAttribute("items", cartItems);
        model.addAttribute("user", loggedInUser);
        LoggerFactory.getLogger(LoggerFactory.DEBUG).log("Error in te payment or while saving the order");

        return "checkout";
    }
    /**
     * Handles the call to add an item in the cart
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/buyProduct"}, method = RequestMethod.GET)
    public String buyProduct(HttpServletRequest request,
                             @RequestParam(value = "id") String itemId,
                             Model model) {
        Item i = itemDao.getItem(itemId);
        cartItems.add(i);
        model.addAttribute("items", itemDao.getAllItems());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("message", "Item " + i.getItem_name() + " has been added to your cart");

        LoggerFactory.getLogger(LoggerFactory.INFO).log("Item " + i.getItem_name() + " has been added to your cart");

        return "productList";
    }

    /**
     * Looks for the orderlist get call and returns all the previous order
     * based on the logged in user
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/orderList"}, method = RequestMethod.GET)
    public String getOrders(HttpServletRequest request, Model model) {

        ArrayList<Order> orders = loggedInUser.getRole().equals("admin") ? orderDao.getAllOrders() : orderDao.getOrdersByUser(loggedInUser);

        LoggerFactory.getLogger(LoggerFactory.DEBUG).log("Fetch orders called by user " + loggedInUser.getUsername() + " with role: " + loggedInUser.getRole());
        model.addAttribute("orders", orders);
        model.addAttribute("user", loggedInUser);
        return "orderList";
    }

    /**
     * Search for the items based on the name
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/searchProducts"}, method = RequestMethod.POST)
    public String searchProducts(HttpServletRequest request,
                                 @RequestParam(value = "searchKey") String search,
                                 Model model) {

        ArrayList<Item> items = itemDao.searchByName(search);
        if (items.size() == 0) {
            model.addAttribute("message","Sorry, There are no items with the key " +search + " !!");
            LoggerFactory.getLogger(LoggerFactory.INFO).log("Sorry, There are no items with the key " +search + " !!");

        }

        LoggerFactory.getLogger(LoggerFactory.DEBUG).log("Searching for items with the key " +search);
        model.addAttribute("items", items);
        model.addAttribute("user", loggedInUser);
        return "searchProducts";
    }

    /**
     * Returns the page where you can search for the itemss in the store.
     * @param request
     * @param model
     * @return
     */

    @RequestMapping(value = {"/searchProducts"}, method = RequestMethod.GET)
    public String searchProducts(HttpServletRequest request,
                                 Model model) {

        model.addAttribute("items", null);
        model.addAttribute("user", loggedInUser);
        return "searchProducts";
    }

}
