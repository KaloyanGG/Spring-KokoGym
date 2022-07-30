package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_items"/* ,uniqueConstraints = { @UniqueConstraint(columnNames = { "product", "shoppingCart" }) }*/)
public class CartItemEntity extends BaseEntity {

    @ManyToOne
    private ProductEntity product;

    private int quantity;

    @ManyToOne
    private ShoppingCartEntity shoppingCart;

    public ProductEntity getProduct() {
        return product;
    }

    public CartItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public CartItemEntity setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
        return this;
    }



}
