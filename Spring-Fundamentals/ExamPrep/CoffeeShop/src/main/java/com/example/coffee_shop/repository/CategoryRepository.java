package com.example.coffee_shop.repository;

import com.example.coffee_shop.model.entity.CategoryEntity;
import com.example.coffee_shop.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(CategoryNameEnum categoryNameEnum);
}
