package com.example.mobilelele.service;

import com.example.mobilelele.model.service.UserLoginServiceModel;

public interface UserService {
    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();

    void initializeUsersAndRoles();
}
