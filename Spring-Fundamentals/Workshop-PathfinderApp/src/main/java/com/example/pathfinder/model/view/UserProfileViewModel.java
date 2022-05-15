package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.enums.LevelEnum;

public class UserProfileViewModel {
    // private Long id;
    private String username;
    private String fullName;
    private Integer age;
    private LevelEnum level;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserProfileViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }
}
