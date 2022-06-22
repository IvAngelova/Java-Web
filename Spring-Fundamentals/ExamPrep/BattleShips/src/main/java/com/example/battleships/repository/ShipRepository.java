package com.example.battleships.repository;

import com.example.battleships.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    boolean existsByName(String name);

    @Query("select s from ShipEntity s order by s.name, s.health, s.power")
    List<ShipEntity> findAllByOrderByStatus();

}
