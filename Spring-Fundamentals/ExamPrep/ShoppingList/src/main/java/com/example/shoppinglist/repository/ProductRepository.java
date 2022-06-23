package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.ProductEntity;
import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    @Query("SELECT SUM(p.price) from ProductEntity p")
    Optional<BigDecimal> getTotalPrice();

    List<ProductEntity> findAllByCategory_Name(CategoryNameEnum category_name);
}
