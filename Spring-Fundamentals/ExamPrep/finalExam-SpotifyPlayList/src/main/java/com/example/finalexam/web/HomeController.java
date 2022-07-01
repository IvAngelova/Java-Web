package com.example.finalexam.web;

import com.example.finalexam.model.entity.enums.StyleNameEnum;
import com.example.finalexam.model.service.SongServiceModel;
import com.example.finalexam.model.view.SongViewModel;
import com.example.finalexam.service.SongService;
import com.example.finalexam.service.UserService;
import com.example.finalexam.user.CurrentUser;
import com.example.finalexam.util.TimeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final SongService songService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeController(CurrentUser currentUser, SongService songService, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.songService = songService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "index";
        }

        model.addAttribute("popSongs", songService.findAllSongsByStyleNameEnum(StyleNameEnum.POP));
        model.addAttribute("jazzSongs", songService.findAllSongsByStyleNameEnum(StyleNameEnum.JAZZ));
        model.addAttribute("rockSongs", songService.findAllSongsByStyleNameEnum(StyleNameEnum.ROCK));

        List<SongServiceModel> allSongsByUser = userService.getAllSongsByUser(currentUser.getId());

        int totalDuration = allSongsByUser
                .stream()
                .mapToInt(SongServiceModel::getDuration)
                .sum();

        String totalDurationStringFormat = TimeConverter.calculateTime(totalDuration);

        model.addAttribute("totalDuration", totalDurationStringFormat);

        List<SongViewModel> playlist = allSongsByUser
                .stream()
                .map(songServiceModel -> {
                    SongViewModel songViewModel = modelMapper.map(songServiceModel, SongViewModel.class);
                    songViewModel.setDuration(TimeConverter.calculateTime(songServiceModel.getDuration()));
                    return songViewModel;
                })
                .collect(Collectors.toList());

        model.addAttribute("playlist", playlist);

        return "home";
    }
}

