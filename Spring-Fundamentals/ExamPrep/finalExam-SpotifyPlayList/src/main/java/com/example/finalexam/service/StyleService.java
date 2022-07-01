package com.example.finalexam.service;

import com.example.finalexam.model.entity.StyleEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;

public interface StyleService {
    void initStyles();

    StyleEntity findByStyleNameEnum(StyleNameEnum styleNameEnum);
}
