package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    boolean existsByName(String name);

    void addProduct(ProductServiceModel productServiceModel);


    void buyAllProducts();

    void buyProduct(Long id);

    BigDecimal getTotalPriceOfProducts();

    List<ProductViewModel> findAllProductsByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
