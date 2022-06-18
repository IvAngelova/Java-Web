package com.example.gira.model.view;

import com.example.gira.model.entity.enums.ClassificationNameEnum;
import com.example.gira.model.entity.enums.ProgressEnum;

import java.time.LocalDate;

public class TaskViewModel {
    private Long id;
    private String name;
    private ProgressEnum progress;
    private LocalDate dueDate;
    private ClassificationNameEnum classification;
    private String user;

    public TaskViewModel() {
    }

    public Long getId() {
        return id;
    }

    public TaskViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskViewModel setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskViewModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public TaskViewModel setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
        return this;
    }

    public String getUser() {
        return user;
    }

    public TaskViewModel setUser(String user) {
        this.user = user;
        return this;
    }
}
