package com.example.finalexam.repository;

import com.example.finalexam.model.entity.SongEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    boolean existsByPerformer(String performer);

    List<SongEntity> findAllByStyle_Name(StyleNameEnum style_name);

}
