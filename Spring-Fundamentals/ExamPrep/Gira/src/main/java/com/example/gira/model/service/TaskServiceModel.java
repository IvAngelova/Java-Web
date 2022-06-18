package com.example.gira.model.service;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.entity.UserEntity;
import com.example.gira.model.entity.enums.ClassificationNameEnum;
import com.example.gira.model.entity.enums.ProgressEnum;

import java.time.LocalDate;

public class TaskServiceModel {

    private Long id;

    private String name;
    private String description;

    private ProgressEnum progress;

    private LocalDate dueDate;

    private ClassificationNameEnum classification;

    private UserEntity user;

    public TaskServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public TaskServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskServiceModel setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskServiceModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public TaskServiceModel setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TaskServiceModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
