package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.CategoryNameEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private CategoryNameEnum name;
    @ManyToMany()
    private List<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryNameEnum categoryNameEnum() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public List<ProductEntity> products() {
        return products;
    }

    public CategoryEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public CategoryNameEnum categoryName() {
        return name;
    }
}
