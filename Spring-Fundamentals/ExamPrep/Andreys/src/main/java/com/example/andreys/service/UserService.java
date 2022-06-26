package com.example.andreys.service;

import com.example.andreys.model.service.UserServiceModel;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);
}
