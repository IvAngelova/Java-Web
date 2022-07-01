package com.example.finalexam.repository;

import com.example.finalexam.model.entity.StyleEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Long> {

    StyleEntity findByName(StyleNameEnum name);
}
