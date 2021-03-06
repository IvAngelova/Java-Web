package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.CategoryEntity;
import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.service.CategoryService;
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
                    .forEach(categoryNameEnum -> {
                        CategoryEntity category = new CategoryEntity();
                        category.setName(categoryNameEnum);
                        category.setDescription(String.format("Description for %s.", categoryNameEnum.name()));
                        categoryRepository.save(category);
                    });
        }

    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {

        return categoryRepository.findByName(categoryNameEnum);
    }
}
