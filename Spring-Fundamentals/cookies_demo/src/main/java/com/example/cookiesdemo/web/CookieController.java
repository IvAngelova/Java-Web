package com.example.cookiesdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    @GetMapping("/cookies")
    public String cookies
            (@CookieValue(name = "lang", defaultValue = "en") String langCookieValue,
             Model model) {
        //below the lang parameter/attribute will become available
        //in the thymeleaf template under the name ${lang}
        model.addAttribute("lang", langCookieValue);
        return "cookies";
    }

    @PostMapping("/cookies")
    public String submitCookies(@RequestParam(name = "language") String langFromHtmlForm,
                                HttpServletResponse response) {
        Cookie langCookie = new Cookie("lang", langFromHtmlForm);
        response.addCookie(langCookie);

        System.out.println("Preferred user language is: " + langFromHtmlForm);

        return "redirect:/cookies";

    }


}
