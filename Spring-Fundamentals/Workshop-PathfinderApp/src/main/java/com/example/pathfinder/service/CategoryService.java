package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.entity.enums.CategoryNameEnum;

public interface CategoryService {

    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
