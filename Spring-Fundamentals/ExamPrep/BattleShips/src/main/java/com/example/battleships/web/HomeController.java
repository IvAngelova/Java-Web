package com.example.battleships.web;

import com.example.battleships.model.binding.HomeBindingModel;
import com.example.battleships.model.view.ShipViewModel;
import com.example.battleships.service.ShipService;
import com.example.battleships.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        List<ShipViewModel> allShipsOrderByStatus = shipService.findAllShipsOrderByStatus();
        model.addAttribute("allShips", allShipsOrderByStatus);

        List<ShipViewModel> attackerShips = allShipsOrderByStatus
                .stream()
                .filter(s -> Objects.equals(s.getUser().getId(), currentUser.getId()))
                .toList();

        List<ShipViewModel> defenderShips = allShipsOrderByStatus
                .stream()
                .filter(s -> !Objects.equals(s.getUser().getId(), currentUser.getId()))
                .toList();

        model.addAttribute("attackerShips", attackerShips);
        model.addAttribute("defenderShips", defenderShips);

        return "home";

    }

    @PostMapping("/")
    public String battle(@Valid HomeBindingModel homeBindingModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (currentUser.getId() == null) {
            return "index";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(
                            "homeBindingModel", homeBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.homeBindingModel", bindingResult);

            return "redirect:/";
        }

        this.shipService.fight(homeBindingModel);

        return "redirect:/";
    }


    @ModelAttribute
    public HomeBindingModel homeBindingModel() {
        return new HomeBindingModel();
    }
}
