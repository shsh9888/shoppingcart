package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CategoryDAOInterface {
    Boolean addCategory(Category category);

    Boolean deleteCategory(String categoryId);

    Category getCategory(String categoryName);

    ArrayList<Category> getAll();
}

