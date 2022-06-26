package com.example.andreys.model.binding;


import com.example.andreys.model.entity.enums.CategoryNameEnum;
import com.example.andreys.model.entity.enums.GenderEnum;


import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ItemAddBindingModel {
    @NotBlank(message = "Cannot be empty!")
    @Size(min = 3, message = "Username length must be more than two characters!")
    private String name;
    @NotBlank(message = "Cannot be empty!")
    @Size(min = 4, message = "Description length must be more than three characters!")
    private String description;
    @Positive(message = "Price must be positive!")
    @NotNull(message = "Cannot be empty!")
    private BigDecimal price;
    @NotNull(message = "You must select category!")
    private CategoryNameEnum category;
    @NotNull(message = "You must select gender!")
    private GenderEnum gender;

    public ItemAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ItemAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public ItemAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public ItemAddBindingModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
