package com.example.andreys.model.view;

import com.example.andreys.model.entity.enums.CategoryNameEnum;
import com.example.andreys.model.entity.enums.GenderEnum;

import java.math.BigDecimal;

public class ItemViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private CategoryNameEnum category;
    private GenderEnum gender;

    public ItemViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ItemViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ItemViewModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemViewModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
