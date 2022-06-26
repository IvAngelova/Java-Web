package com.example.andreys.service;

import com.example.andreys.model.entity.CategoryEntity;
import com.example.andreys.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
