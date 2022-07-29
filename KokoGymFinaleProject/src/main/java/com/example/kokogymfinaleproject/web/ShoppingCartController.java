package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {


    @GetMapping()
    public String shoppingCart(Model model, @AuthenticationPrincipal KokoGymUserDetails userDetails) {

        model.addAttribute("sum", userDetails.getShoppingCart().getSum());
        model.addAttribute("count", userDetails.getShoppingCart().getCountOfAllItems());

        return "shoppingCart";
    }


}
