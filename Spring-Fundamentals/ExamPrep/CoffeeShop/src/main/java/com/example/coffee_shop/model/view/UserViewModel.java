package com.example.coffee_shop.model.view;

public class UserViewModel {
    private String username;
    private Integer totalOrders;

    public UserViewModel() {
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public UserViewModel setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
