package com.example.finalexam.model.entity;

import com.example.finalexam.model.entity.enums.StyleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class StyleEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public StyleEntity() {
    }

    public StyleNameEnum getName() {
        return name;
    }

    public StyleEntity setName(StyleNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StyleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
