package com.example.musicdb.service;

import com.example.musicdb.model.entity.UserEntity;
import com.example.musicdb.model.service.UserServiceModel;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findById(Long id);
}
