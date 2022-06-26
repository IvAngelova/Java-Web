package com.example.andreys.model.binding;


import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UserRegisterBindingModel {

    @Size(min = 3, message = "Username length must be more than two characters!")
    @NotBlank(message = "Cannot be empty!")
    private String username;

    @Size(min = 3, message = "Password length must be more than two characters!")
    @NotNull(message = "Cannot be empty!")
    private String password;

    @Size(min = 3, message = "Password length must be more than two characters!")
    @NotNull(message = "Cannot be empty!")
    private String confirmPassword;

    @NotBlank(message = "Cannot be empty!")
    @Email(message = "Email must contain '@'.")
    private String email;

    @DecimalMin(value = "0.0", message = "Budget must be more or equal to 0!")
    @NotNull(message = "Cannot be empty!")
    private BigDecimal budget;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public UserRegisterBindingModel setBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }
}
