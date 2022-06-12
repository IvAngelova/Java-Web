package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.RoleEnum;
import com.example.mobilelele.model.service.UserRegistrationServiceModel;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.repository.UserRoleRepository;
import com.example.mobilelele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }



    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserRole userRole = userRoleRepository.findByRole(RoleEnum.USER);

        User newUser = new User();
        newUser.setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setActive(true)
                .setRoles(Set.of(userRole));

        userRepository.save(newUser);

        //TODO:Register user
       // login(newUser);
    }

    @Override
    public boolean isUsernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .isEmpty();
    }


    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRole adminRole = userRoleRepository.findByRole(RoleEnum.ADMIN);
            UserRole userRole = userRoleRepository.findByRole(RoleEnum.USER);

            User admin = new User();
            admin
                    .setActive(true)
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setPassword(passwordEncoder.encode("test"));

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            User pesho = new User();
            pesho
                    .setActive(true)
                    .setUsername("pesho")
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setPassword(passwordEncoder.encode("test"));

            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole().setRole(RoleEnum.ADMIN);
            UserRole userRole = new UserRole().setRole(RoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
