package com.example.gira.repository;

import com.example.gira.model.entity.ClassificationEntity;
import com.example.gira.model.entity.enums.ClassificationNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity, Long> {

    ClassificationEntity findByClassificationName(ClassificationNameEnum classificationName);
}
