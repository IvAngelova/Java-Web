package com.example.musicdb.service;

import com.example.musicdb.model.entity.ArtistEntity;
import com.example.musicdb.model.entity.enums.ArtistNameEnum;

public interface ArtistService {
    void initArtists();

    ArtistEntity findByArtistNameEnum(ArtistNameEnum artistNameEnum);
}
