package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.Role;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.entity.enums.LevelEnum;
import com.example.pathfinder.model.entity.enums.RoleNameEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.RoleRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        User newUser = modelMapper.map(userServiceModel, User.class);

        newUser.setLevel(LevelEnum.BEGINNER);
//        Role userRole = roleRepository.findByRole(RoleNameEnum.USER);
//        newUser.setRoles(Set.of(userRole));

        userRepository.save(newUser);

    }

    @Override
    public boolean isUsernameOccupied(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId());
        currentUser.setUsername(userServiceModel.getUsername());

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
