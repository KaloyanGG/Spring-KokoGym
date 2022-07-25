package com.example.kokogymfinaleproject.model.dto;


public class AddProductDTO {

    private String name;
    private int stockQuantity;
    private String imageUrl;
    private double price;

    public AddProductDTO() {
    }

    public String getName() {
        return name;
    }

    public AddProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public AddProductDTO setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddProductDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public AddProductDTO setPrice(double price) {
        this.price = price;
        return this;
    }
}
