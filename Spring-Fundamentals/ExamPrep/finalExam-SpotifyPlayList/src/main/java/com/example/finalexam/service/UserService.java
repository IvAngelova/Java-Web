package com.example.finalexam.service;

import com.example.finalexam.model.entity.UserEntity;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.model.view.SongViewModel;

import java.util.List;

public interface UserService {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);


    void addSongToPlayList(Long songId);

    List<SongServiceModel> getAllSongsByUser(Long id);

    void deletePlaylist();
}
