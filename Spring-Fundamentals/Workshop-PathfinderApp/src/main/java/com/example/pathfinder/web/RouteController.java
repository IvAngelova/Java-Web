package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.RouteAddBindingModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        model.addAttribute("routeViewModel", routeService.findAllRoutesView());
        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("routeDetailsViewModel", routeService.findRouteViewById(id));
        return "route-details";
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/add")
    public String add() {
        if (currentUser.getId() == null) {
            return "redirect:/users/login";
        }
        return "add-route";
    }

    @PostMapping("/add")
    public String addRouteConfirm(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper.map(routeAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }

}
