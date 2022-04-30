package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.service.UserLoginServiceModel;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<User> byUsername = userRepository.findByUsername(loginServiceModel.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        return passwordEncoder
                .matches(loginServiceModel.getRawPassword(), byUsername.get().getPassword());

    }
}
