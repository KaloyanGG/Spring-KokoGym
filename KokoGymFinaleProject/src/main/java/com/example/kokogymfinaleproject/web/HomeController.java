package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.component.DiscountScheduler;
import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final DiscountScheduler discountScheduler;

    public HomeController(DiscountScheduler discountScheduler) {
        this.discountScheduler = discountScheduler;
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal KokoGymUserDetails user) {

        // if user is not logged in, redirect to home page
        if (user != null) {
            return "home";
        }

        // if user is logged in, redirect to index page
        return "index";
    }


}
