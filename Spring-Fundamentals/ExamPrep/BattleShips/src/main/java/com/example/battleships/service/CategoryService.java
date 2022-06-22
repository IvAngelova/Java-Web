package com.example.battleships.service;

import com.example.battleships.model.entity.CategoryEntity;
import com.example.battleships.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findByCategoryName(CategoryNameEnum categoryNameEnum);
}
