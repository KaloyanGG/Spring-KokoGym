package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories;
    private int stockQuantity;
    private double price;

    public ProductEntity() {
    }

    public String name() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<CategoryEntity> categoryEntity() {
        return categories;
    }

    public ProductEntity setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public int stockQuantity() {
        return stockQuantity;
    }

    public ProductEntity setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
        return this;
    }

    public double price() {
        return price;
    }

    public ProductEntity setPrice(double price) {
        this.price = price;
        return this;
    }
}
