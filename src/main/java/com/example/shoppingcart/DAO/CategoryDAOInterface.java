package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Interface exposes all the db operations to be done with resepct to Category table
 */
@Service
public interface CategoryDAOInterface {
    Boolean addCategory(Category category);

    Boolean deleteCategory(String categoryId);

    Category getCategory(String categoryName);

    ArrayList<Category> getAll();
}

