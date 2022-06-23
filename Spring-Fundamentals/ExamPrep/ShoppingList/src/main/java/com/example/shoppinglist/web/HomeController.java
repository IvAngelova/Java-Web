package com.example.shoppinglist.web;


import com.example.shoppinglist.model.entity.enums.CategoryNameEnum;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "index";
        }

        model.addAttribute("totalPriceOfProducts", productService.getTotalPriceOfProducts());
        model.addAttribute("drinks",
                productService.findAllProductsByCategoryNameEnum(CategoryNameEnum.DRINK));
        model.addAttribute("other",
                productService.findAllProductsByCategoryNameEnum(CategoryNameEnum.OTHER));
        model.addAttribute("food",
                productService.findAllProductsByCategoryNameEnum(CategoryNameEnum.FOOD));
        model.addAttribute("households",
                productService.findAllProductsByCategoryNameEnum(CategoryNameEnum.HOUSEHOLD));

        return "home";
    }
}
