package com.example.gira.model.entity;

import com.example.gira.model.entity.enums.ClassificationNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class ClassificationEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ClassificationNameEnum classificationName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public ClassificationEntity() {
    }

    public ClassificationNameEnum getClassificationName() {
        return classificationName;
    }

    public ClassificationEntity setClassificationName(ClassificationNameEnum classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClassificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
