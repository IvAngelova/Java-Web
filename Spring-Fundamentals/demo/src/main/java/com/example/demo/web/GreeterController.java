package com.example.demo.web;

import com.example.demo.service.GreeterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreeterController {

    private final GreeterService greeterService;

    public GreeterController(GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "person", defaultValue = "Anonymous")
                        String person, Model model){

        model.addAttribute("greeting", greeterService.greeting(person));

        return "hello";
    }
}
