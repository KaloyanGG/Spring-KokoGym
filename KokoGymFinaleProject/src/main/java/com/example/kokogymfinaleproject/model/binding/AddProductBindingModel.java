package com.example.kokogymfinaleproject.model.binding;


import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddProductBindingModel {

    @NotBlank(message = "Name should be provided.")
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters.")
    private String name;
    @NotNull(message = "Stock quantity should be provided.")
    @Positive(message = "Stock quantity should be positive.")
    private Integer stockQuantity;
    @NotBlank(message = "Image url should be provided.")
    private String imageUrl;
    @NotNull(message = "Price should be provided.")
    @Positive(message = "Price should be positive.")
    private Double price;

    public AddProductBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public AddProductBindingModel setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddProductBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public AddProductBindingModel setPrice(Double price) {
        this.price = price;
        return this;
    }
}
