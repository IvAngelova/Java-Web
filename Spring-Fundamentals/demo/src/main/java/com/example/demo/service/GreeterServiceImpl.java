package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreeterServiceImpl implements GreeterService {
    @Override
    public String greeting(String person) {
        return "Hello, " + person + "!";
    }
}
