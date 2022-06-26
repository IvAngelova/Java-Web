package com.example.andreys.model.entity;

import com.example.andreys.model.entity.enums.GenderEnum;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne(optional = false)
    private CategoryEntity category;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum gender;

    public ItemEntity() {
    }

    public String getName() {
        return name;
    }

    public ItemEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ItemEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
