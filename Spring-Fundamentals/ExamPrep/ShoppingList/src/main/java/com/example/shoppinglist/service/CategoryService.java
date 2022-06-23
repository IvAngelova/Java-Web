package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.CategoryEntity;
import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
