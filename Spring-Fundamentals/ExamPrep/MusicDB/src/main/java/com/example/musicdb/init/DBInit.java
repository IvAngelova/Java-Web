package com.example.musicdb.init;

import com.example.musicdb.service.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final ArtistService artistService;

    public DBInit(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        artistService.initArtists();
    }
}
