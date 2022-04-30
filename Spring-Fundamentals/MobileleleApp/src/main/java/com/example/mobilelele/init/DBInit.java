package com.example.mobilelele.init;

import com.example.mobilelele.model.entity.Brand;
import com.example.mobilelele.model.entity.Model;
import com.example.mobilelele.model.entity.User;
import com.example.mobilelele.model.entity.enums.Category;
import com.example.mobilelele.repository.BrandRepository;
import com.example.mobilelele.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DBInit(BrandRepository brandRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        initializeBrandAndModels();
        initializeUsers();


    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin
                    .setActive(true)
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setPassword(passwordEncoder.encode("test"));

            userRepository.save(admin);
        }
    }


    private void initializeBrandAndModels() {
        if (brandRepository.count() == 0) {
            Brand ford = new Brand().setName("Ford");

            Model fiesta = new Model()
                    .setBrand(ford)
                    .setName("Fiesta")
                    .setCategory(Category.CAR)
                    .setStartYear(1976);

            Model escort = new Model()
                    .setBrand(ford)
                    .setName("Escort")
                    .setCategory(Category.CAR)
                    .setStartYear(1967)
                    .setEndYear(2004);

            ford.setModels(List.of(fiesta, escort));

            brandRepository.save(ford);

        }
    }
}
