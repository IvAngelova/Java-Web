package com.example.gira.service;

import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    boolean existsByName(String name);

    void addTask(TaskServiceModel taskServiceModel);

    List<TaskViewModel> findAllTasks();

    void progressTask(Long id);
}
