package com.example.andreys.web;

import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.service.ItemService;
import com.example.andreys.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ItemService itemService;

    public HomeController(CurrentUser currentUser, ItemService itemService) {
        this.currentUser = currentUser;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "index";
        }

        List<ItemViewModel> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("totalCount", items.size());

        return "home";


    }


}
