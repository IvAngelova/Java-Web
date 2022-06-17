package com.example.coffee_shop.service.impl;

import com.example.coffee_shop.model.entity.CategoryEntity;
import com.example.coffee_shop.model.entity.enums.CategoryNameEnum;
import com.example.coffee_shop.repository.CategoryRepository;
import com.example.coffee_shop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .map(categoryNameEnum -> {
                        CategoryEntity category = new CategoryEntity();
                        category.setName(categoryNameEnum);
                        switch (categoryNameEnum) {
                            case CAKE -> category.setNeededTime(10);
                            case DRINK -> category.setNeededTime(1);
                            case COFFEE -> category.setNeededTime(2);
                            case OTHER -> category.setNeededTime(5);
                        }

                        return category;
                    })
                    .forEach(categoryRepository::save);
        }

    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {

        return categoryRepository.findByName(categoryNameEnum);
    }
}
