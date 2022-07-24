package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    private ProductService productService;

    public ShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String shop(Model model) {

        model.addAttribute("products",this.productService.findAll());

        return "shop";
    }

}
