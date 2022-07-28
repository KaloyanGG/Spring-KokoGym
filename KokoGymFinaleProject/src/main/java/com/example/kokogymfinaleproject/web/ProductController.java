package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.dto.AddProductDTO;
import com.example.kokogymfinaleproject.model.entity.ProductEntity;
import com.example.kokogymfinaleproject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct() {
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProductConfirm(AddProductDTO addProductDTO) {

        ProductEntity product = new ProductEntity(
                addProductDTO.getName(),
                addProductDTO.getStockQuantity(),
                addProductDTO.getPrice(),
                addProductDTO.getImageUrl()
        );
        this.productService.add(product);

    //todo add categories in shop
        return"redirect:/shop";

    }

}
