package com.example.battleships.repository;

import com.example.battleships.model.entity.CategoryEntity;
import com.example.battleships.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(CategoryNameEnum name);
}
