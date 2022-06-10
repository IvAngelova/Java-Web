package com.example.mobilelele.web;

import com.example.mobilelele.model.view.BrandView;
import com.example.mobilelele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BrandController {

    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public BrandController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("brands/all")
    public String allBrands(Model model){
        model.addAttribute("allBrands", brandService.getAllBrands());
        return "brands";
    }

}
