package com.example.finalexam.service.impl;

import com.example.finalexam.model.entity.SongEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.view.SongViewModel;
import com.example.finalexam.repository.SongRepository;
import com.example.finalexam.service.SongService;
import com.example.finalexam.service.StyleService;
import com.example.finalexam.util.TimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
    }

    @Override
    public void addSong(SongServiceModel songServiceModel) {
        SongEntity songEntity = modelMapper.map(songServiceModel, SongEntity.class);
        songEntity.setStyle(styleService.findByStyleNameEnum(songServiceModel.getStyle()));
        songRepository.save(songEntity);
    }

    @Override
    public boolean existsByPerformer(String performer) {
        return songRepository.existsByPerformer(performer);
    }

    @Override
    public List<SongViewModel> findAllSongsByStyleNameEnum(StyleNameEnum styleNameEnum) {

        return songRepository.findAllByStyle_Name(styleNameEnum)
                .stream()
                .map(songEntity ->{
                    SongViewModel songViewModel = modelMapper.map(songEntity, SongViewModel.class);
                    songViewModel.setDuration(TimeConverter.calculateTime(songEntity.getDuration()));
                    return songViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SongEntity findSongById(Long songId) {

        return songRepository.findById(songId).orElse(null);

    }

}
