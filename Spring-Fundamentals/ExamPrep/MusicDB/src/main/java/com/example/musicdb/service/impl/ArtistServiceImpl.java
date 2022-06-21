package com.example.musicdb.service.impl;

import com.example.musicdb.model.entity.ArtistEntity;
import com.example.musicdb.model.entity.enums.ArtistNameEnum;
import com.example.musicdb.repository.ArtistRepository;
import com.example.musicdb.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void initArtists() {
        if (artistRepository.count() == 0) {
            Arrays.stream(ArtistNameEnum.values())
                    .map(artistNameEnum -> {
                        ArtistEntity artist = new ArtistEntity();
                        artist.setName(artistNameEnum);
                        return artist;
                    })
                    .forEach(artistRepository::save);
        }
    }

    @Override
    public ArtistEntity findByArtistNameEnum(ArtistNameEnum artistNameEnum) {

        return artistRepository.findByName(artistNameEnum);
    }
}
