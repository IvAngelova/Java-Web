package com.example.coffee_shop.web;

import com.example.coffee_shop.model.view.OrderViewModel;
import com.example.coffee_shop.service.OrderService;
import com.example.coffee_shop.service.UserService;
import com.example.coffee_shop.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrdersOrderByPriceDesc();

        model.addAttribute("orders", orders);

        Integer totalNeededTime = orders.stream()
                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce((a, b) -> a + b)
                .orElse(0);

        model.addAttribute("totalNeededTime", totalNeededTime);

        model.addAttribute("employees", userService.getAllEmployeesOrderByCountOfOrdersDesc());

        return "home";


    }
}
