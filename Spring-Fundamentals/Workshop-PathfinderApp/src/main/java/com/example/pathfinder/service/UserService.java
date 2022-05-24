package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean isUsernameOccupied(String username);

    void loginUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void logout();

    UserServiceModel findByID(Long id);

    User findCurrentLoginUserEntity();
}
