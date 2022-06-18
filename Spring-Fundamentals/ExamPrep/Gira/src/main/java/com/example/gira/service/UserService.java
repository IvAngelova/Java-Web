package com.example.gira.service;

import com.example.gira.model.entity.UserEntity;
import com.example.gira.model.service.UserServiceModel;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByEmailAndPassword(String email, String password);

    void loginUser(Long id, String username);

    UserEntity findUserById(Long id);
}
