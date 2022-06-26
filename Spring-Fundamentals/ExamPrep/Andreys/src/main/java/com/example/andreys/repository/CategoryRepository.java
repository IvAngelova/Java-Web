package com.example.andreys.repository;

import com.example.andreys.model.entity.CategoryEntity;
import com.example.andreys.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryNameEnum name);
}
