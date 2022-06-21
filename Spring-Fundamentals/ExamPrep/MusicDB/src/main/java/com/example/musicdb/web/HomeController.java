package com.example.musicdb.web;

import com.example.musicdb.model.view.AlbumViewModel;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }
        List<AlbumViewModel> albums = albumService.findAllAlbumsOrderByCopiesDesc();
        model.addAttribute("albums", albums);
        int totalCopies = albums
                .stream()
                .mapToInt(AlbumViewModel::getCopies)
                .sum();
        model.addAttribute("totalCopies", totalCopies);

        return "home";


    }
}
