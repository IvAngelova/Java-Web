package com.example.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
    @GetMapping("/orders/{id}/details")
    public String findOrderById(@PathVariable("id") Long orderId){
        throw new ObjectNotFoundException(orderId);
    }
}
