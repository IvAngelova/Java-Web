package com.example.finalexam.service.impl;

import com.example.finalexam.model.entity.SongEntity;
import com.example.finalexam.model.entity.UserEntity;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.model.view.SongViewModel;
import com.example.finalexam.repository.UserRepository;
import com.example.finalexam.service.SongService;
import com.example.finalexam.service.UserService;
import com.example.finalexam.user.CurrentUser;
import com.example.finalexam.util.TimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final SongService songService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, SongService songService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.songService = songService;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = modelMapper.map(userServiceModel, UserEntity.class);
        userRepository.save(userEntity);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser
                .setId(id)
                .setUsername(username);
    }


    @Override
    public void addSongToPlayList(Long songId) {
        UserEntity loggedInUser = userRepository.findById(currentUser.getId()).orElse(null);
        SongEntity songById = songService.findSongById(songId);

        assert loggedInUser != null;
        if (loggedInUser.getPlaylist().contains(songById)) {
            return;
        }

        loggedInUser.getPlaylist().add(songById);
        userRepository.save(loggedInUser);
    }

    @Override
    public List<SongServiceModel> getAllSongsByUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userEntity.getPlaylist().stream()
                .map(songEntity -> modelMapper.map(songEntity, SongServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlaylist() {
        UserEntity loggedInUser = userRepository.findById(currentUser.getId()).orElse(null);
        loggedInUser.getPlaylist().clear();
        userRepository.save(loggedInUser);
    }
}
