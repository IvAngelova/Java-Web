package com.example.musicdb.service.impl;

import com.example.musicdb.model.entity.AlbumEntity;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.model.view.AlbumViewModel;
import com.example.musicdb.repository.AlbumRepository;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.ArtistService;
import com.example.musicdb.service.UserService;
import com.example.musicdb.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final ModelMapper modelMapper;
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ArtistService artistService;

    public AlbumServiceImpl(ModelMapper modelMapper, AlbumRepository albumRepository, UserService userService, CurrentUser currentUser, ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.currentUser = currentUser;
        this.artistService = artistService;
    }

    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = modelMapper.map(albumServiceModel, AlbumEntity.class);
        albumEntity.setAddedFrom(userService.findById(currentUser.getId()));
        albumEntity.setArtist(artistService.findByArtistNameEnum(albumServiceModel.getArtist()));

        albumRepository.save(albumEntity);
    }

    @Override
    public List<AlbumViewModel> findAllAlbumsOrderByCopiesDesc() {
       return albumRepository.findAllByOrderByCopiesDesc()
                .stream()
                .map(albumEntity -> {
                    AlbumViewModel albumViewModel = modelMapper.map(albumEntity, AlbumViewModel.class);
                    String artistNameToLower = albumEntity.getArtist().getName().name().toLowerCase();
                    char firstLetter = Character.toUpperCase(artistNameToLower.charAt(0));
                    String artistName = artistNameToLower.replace(artistNameToLower.charAt(0), firstLetter);
                    albumViewModel.setArtist(artistName);
                    return albumViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }


}
