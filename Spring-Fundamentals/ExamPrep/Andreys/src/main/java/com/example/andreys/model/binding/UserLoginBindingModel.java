package com.example.andreys.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    @Size(min = 3, message = "Username length must be more than two characters!")
    @NotBlank(message = "Cannot be empty!")
    private String username;

    @Size(min = 3, message = "Password length must be more than two characters!")
    @NotNull(message = "Cannot be empty!")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
