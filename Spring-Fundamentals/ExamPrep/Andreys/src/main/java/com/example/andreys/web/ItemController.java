package com.example.andreys.web;

import com.example.andreys.model.binding.ItemAddBindingModel;
import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public ItemController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);

            return "redirect:add";
        }

        // unique name
        if (itemService.existsByName(itemAddBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("isNameTaken", true);
            return "redirect:add";
        }


        itemService.addItem(modelMapper.map(itemAddBindingModel, ItemServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public ItemAddBindingModel itemAddBindingModel() {
        return new ItemAddBindingModel();
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {

        ItemViewModel item = itemService.findItemById(id);

        model.addAttribute("item", item);

        return "details-item";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/";

    }


}
