package com.example.mobilelele.service;

import com.example.mobilelele.model.service.UserLoginServiceModel;
import com.example.mobilelele.model.service.UserRegistrationServiceModel;

public interface UserService {
    boolean login(UserLoginServiceModel loginServiceModel);

    void logout();

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
}
