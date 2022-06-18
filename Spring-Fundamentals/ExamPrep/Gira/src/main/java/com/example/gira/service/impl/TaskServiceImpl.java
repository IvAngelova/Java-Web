package com.example.gira.service.impl;

import com.example.gira.model.entity.TaskEntity;
import com.example.gira.model.entity.enums.ProgressEnum;
import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.view.TaskViewModel;
import com.example.gira.repository.TaskRepository;
import com.example.gira.service.ClassificationService;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import com.example.gira.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ClassificationService classificationService;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, ClassificationService classificationService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.classificationService = classificationService;
    }

    @Override
    public boolean existsByName(String name) {
        return taskRepository.existsByName(name);
    }

    @Override
    public void addTask(TaskServiceModel taskServiceModel) {

        TaskEntity taskEntity = modelMapper.map(taskServiceModel, TaskEntity.class);
        taskEntity.setUser(userService.findUserById(currentUser.getId()));
        taskEntity.setProgress(ProgressEnum.OPEN);
        taskEntity.setClassification(classificationService.findByClassificationNameEnum(taskServiceModel.getClassification()));

        taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskViewModel> findAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskEntity -> {
                    TaskViewModel taskViewModel = modelMapper.map(taskEntity, TaskViewModel.class);
                    taskViewModel.setUser(taskEntity.getUser().getUsername());
                    return taskViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void progressTask(Long id) {

        TaskEntity task = taskRepository.findById(id).get();

        if (task.getProgress().name().equalsIgnoreCase("completed")) {
            taskRepository.deleteById(id);
            return;
        }

        if (task.getProgress().name().equalsIgnoreCase("open")) {
            task.setProgress(ProgressEnum.IN_PROGRESS);
        } else if (task.getProgress().name().equalsIgnoreCase("in_progress")) {
            task.setProgress(ProgressEnum.COMPLETED);
        }

        taskRepository.save(task);

    }
}
