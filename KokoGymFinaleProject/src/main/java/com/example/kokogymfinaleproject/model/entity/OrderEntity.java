package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne(optional = false)
    private ProductEntity product;
    @ManyToOne(optional = false)
    private UserEntity user;
    @Column(nullable = false)
    private int quantity;
    @ManyToOne(optional = false)
    private Discount discount;

    public OrderEntity() {
    }

    public ProductEntity product() {
        return product;
    }

    public OrderEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public UserEntity user() {
        return user;
    }

    public OrderEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public int quantity() {
        return quantity;
    }

    public OrderEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Discount discount() {
        return discount;
    }

    public OrderEntity setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }
}
