package com.example.finalexam.service.impl;

import com.example.finalexam.model.entity.StyleEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;
import com.example.finalexam.repository.StyleRepository;
import com.example.finalexam.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if (styleRepository.count() == 0) {
            Arrays.stream(StyleNameEnum.values())
                    .map(styleNameEnum -> {
                        StyleEntity styleEntity = new StyleEntity();
                        styleEntity.setName(styleNameEnum);
                        styleEntity.setDescription(String.format("Description for %s.", styleNameEnum.name()));
                        return styleEntity;
                    })
                    .forEach(styleRepository::save);
        }

    }

    @Override
    public StyleEntity findByStyleNameEnum(StyleNameEnum styleNameEnum) {
        return styleRepository.findByName(styleNameEnum);
    }
}
