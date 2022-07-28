package com.example.kokogymfinaleproject.model.entity;


import org.springframework.core.annotation.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItemEntity extends BaseEntity{

    @ManyToOne(optional = false)
    private ProductEntity product;

    @Column(nullable = false)
    private int quantity;
    @ManyToOne(optional = false)
    private DiscountEntity discountEntity;
    @ManyToOne
    private OrderEntity order;

    public OrderItemEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public OrderItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public DiscountEntity getDiscountEntity() {
        return discountEntity;
    }

    public OrderItemEntity setDiscountEntity(DiscountEntity discountEntity) {
        this.discountEntity = discountEntity;
        return this;
    }
}
