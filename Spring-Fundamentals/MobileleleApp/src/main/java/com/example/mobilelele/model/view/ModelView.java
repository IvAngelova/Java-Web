package com.example.mobilelele.model.view;

import com.example.mobilelele.model.entity.enums.Category;

public class ModelView {
    private Long id;
    private String name;
    private Category category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;

    public ModelView() {
    }

    public Long getId() {
        return id;
    }

    public ModelView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelView setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ModelView setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelView setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelView setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }
}
