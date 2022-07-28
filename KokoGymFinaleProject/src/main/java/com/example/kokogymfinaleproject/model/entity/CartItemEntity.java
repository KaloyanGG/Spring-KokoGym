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



    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}
