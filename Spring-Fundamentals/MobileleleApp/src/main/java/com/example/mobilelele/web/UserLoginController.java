package com.example.mobilelele.web;

import com.example.mobilelele.model.binding.UserLoginBindingModel;
import com.example.mobilelele.model.service.UserLoginServiceModel;
import com.example.mobilelele.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindingModel userLoginBindingModel){

        boolean loginSuccessful = userService.login(new UserLoginServiceModel()
                .setUsername(userLoginBindingModel.getUsername())
                .setRawPassword(userLoginBindingModel.getPassword()));


        LOGGER.info("User with username {} tried to login. Success = {}",
                userLoginBindingModel.getUsername(), loginSuccessful);

        if(loginSuccessful){
            return "redirect:/";
        }

        return "redirect:/users/login";
    }
}
