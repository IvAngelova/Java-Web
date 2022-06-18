package com.example.gira.service.impl;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.entity.enums.ClassificationNameEnum;
import com.example.gira.repository.ClassificationRepository;
import com.example.gira.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void init() {
        if (classificationRepository.count() == 0) {

            Arrays.stream(ClassificationNameEnum.values())
                    .forEach(classificationNameEnum -> {
                        ClassificationEntity classification = new ClassificationEntity();
                        classification.setClassificationName(classificationNameEnum);
                        classification.setDescription(String.format("Description for %s.", classificationNameEnum.name()));
                        classificationRepository.save(classification);
                    });
        }
    }

    @Override
    public ClassificationEntity findByClassificationNameEnum(ClassificationNameEnum classificationNameEnum) {
        return classificationRepository.findByClassificationName(classificationNameEnum);
    }
}
