package com.example.andreys.service.impl;

import com.example.andreys.model.entity.CategoryEntity;
import com.example.andreys.model.entity.enums.CategoryNameEnum;
import com.example.andreys.repository.CategoryRepository;
import com.example.andreys.service.CategoryService;
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
                        category.setDescription(String.format("Description for %s.", categoryNameEnum.name()));
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
