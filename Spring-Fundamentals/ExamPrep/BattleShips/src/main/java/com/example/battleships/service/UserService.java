package com.example.battleships.service;

import com.example.battleships.model.entity.UserEntity;
import com.example.battleships.model.service.UserServiceModel;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);
}
