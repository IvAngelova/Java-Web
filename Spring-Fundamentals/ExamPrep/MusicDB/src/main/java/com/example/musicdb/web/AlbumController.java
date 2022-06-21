package com.example.musicdb.web;

import com.example.musicdb.model.binding.AlbumAddBindingModel;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;

    public AlbumController(ModelMapper modelMapper, AlbumService albumService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
    }

    @GetMapping("/add")
    public String add() {
        return "add-album";
    }

    @ModelAttribute
    public AlbumAddBindingModel albumAddBindingModel() {
        return new AlbumAddBindingModel();
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AlbumAddBindingModel albumAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);

            return "redirect:add";
        }


        albumService.addAlbum(modelMapper.map(albumAddBindingModel, AlbumServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        albumService.deleteAlbum(id);

        return "redirect:/";
    }
}
