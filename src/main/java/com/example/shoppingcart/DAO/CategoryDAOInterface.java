package com.example.shoppingcart.DAO;

import com.example.shoppingcart.entities.Category;

public interface CategoryDAOInterface {
    Boolean addCategory(Category category);

    Boolean deleteCategory(String categoryId);
}

