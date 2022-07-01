package com.example.finalexam.service;

import com.example.finalexam.model.entity.SongEntity;
import com.example.finalexam.model.entity.enums.StyleNameEnum;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.view.SongViewModel;

import java.util.List;

public interface SongService {
    void addSong(SongServiceModel songServiceModel);

    boolean existsByPerformer(String performer);

    List<SongViewModel> findAllSongsByStyleNameEnum(StyleNameEnum styleNameEnum);

    SongEntity findSongById(Long songId);

}
