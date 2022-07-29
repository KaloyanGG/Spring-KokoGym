package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.CartItemEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.service.ProductService;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ProductService productService;
    private final UserService userService;

    public ShopController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping()
    public String shop(Model model) {

        model.addAttribute("products",this.productService.findAll());

        return "shop";
    }
    
    @GetMapping("/addToCart/{id}")
    public String addToCart(Model model, @PathVariable("id") Long id, @AuthenticationPrincipal KokoGymUserDetails userDetails) {
        productService.makeCartItem(id, userDetails.getShoppingCart());
        return "redirect:/shop";
    }
    

}
