package com.example.mobilelele.web;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.service.UserRegistrationServiceModel;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/register")
    public String registerUser(){
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(UserRegistrationBindingModel userModel){

        //TODO: validation

        UserRegistrationServiceModel serviceModel = modelMapper
                .map(userModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(serviceModel);

        return "redirect:/";
    }
}
