package com.example.coffee_shop.service;

import com.example.coffee_shop.model.entity.CategoryEntity;
import com.example.coffee_shop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
