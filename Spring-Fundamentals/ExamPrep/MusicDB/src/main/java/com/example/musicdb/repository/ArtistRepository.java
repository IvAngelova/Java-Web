package com.example.musicdb.repository;

import com.example.musicdb.model.entity.ArtistEntity;
import com.example.musicdb.model.entity.enums.ArtistNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    ArtistEntity findByName(ArtistNameEnum artistNameEnum);
}
