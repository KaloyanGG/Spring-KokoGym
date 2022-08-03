package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.binding.AddProductBindingModel;
import com.example.kokogymfinaleproject.service.ProductService;
import org.hibernate.TypeMismatchException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("productModel")
    public AddProductBindingModel productModel() {
        return new AddProductBindingModel();
    }


    @GetMapping("/add")
    public String addProduct(@AuthenticationPrincipal KokoGymUserDetails principal) {
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid AddProductBindingModel productModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel",
                    bindingResult);
            return "redirect:/products/add";
        }
        this.productService.add(productModel);

        //todo add categories in shop
        return "redirect:/shop";

    }

}
