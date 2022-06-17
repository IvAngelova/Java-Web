package com.example.coffee_shop.service;

import com.example.coffee_shop.model.entity.UserEntity;
import com.example.coffee_shop.model.service.UserServiceModel;
import com.example.coffee_shop.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findUserById(Long id);

    List<UserViewModel> getAllEmployeesOrderByCountOfOrdersDesc();
}
