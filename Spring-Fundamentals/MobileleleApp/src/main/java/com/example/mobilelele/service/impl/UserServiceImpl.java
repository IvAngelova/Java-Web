package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.UserRole;
import com.example.mobilelele.model.entity.enums.Role;
import com.example.mobilelele.model.service.UserLoginServiceModel;
import com.example.mobilelele.repository.UserRepository;
import com.example.mobilelele.repository.UserRoleRepository;
import com.example.mobilelele.service.UserService;
import com.example.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<User> byUsername = userRepository.findByUsername(loginServiceModel.getUsername());

        if (byUsername.isEmpty()) {
            logout();
            return false;
        }

        boolean success = passwordEncoder
                .matches(loginServiceModel.getRawPassword(), byUsername.get().getPassword());

        if (success) {
            User loggedInUser = byUsername.get();
            currentUser.setLoggedIn(true)
                    .setUsername(loggedInUser.getUsername())
                    .setFirstName(loggedInUser.getFirstName())
                    .setLastName(loggedInUser.getLastName());

            loggedInUser.getRoles().forEach(r -> currentUser.addRole(r.getRole()));
        }

        return success;

    }

    @Override
    public void logout() {
        currentUser.clear();
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRole adminRole = userRoleRepository.findByRole(Role.ADMIN);
            UserRole userRole = userRoleRepository.findByRole(Role.USER);

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
            UserRole adminRole = new UserRole().setRole(Role.ADMIN);
            UserRole userRole = new UserRole().setRole(Role.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
