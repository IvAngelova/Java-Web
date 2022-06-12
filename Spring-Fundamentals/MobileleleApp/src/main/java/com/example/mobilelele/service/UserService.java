package com.example.mobilelele.service;


import com.example.mobilelele.model.service.UserRegistrationServiceModel;

public interface UserService {


    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

    boolean isUsernameFree(String username);
}
