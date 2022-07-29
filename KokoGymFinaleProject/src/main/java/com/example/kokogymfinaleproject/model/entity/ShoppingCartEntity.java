package com.example.kokogymfinaleproject.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCartEntity extends BaseEntity {

    @OneToMany(mappedBy = "shoppingCart")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CartItemEntity> cartItems;

    public ShoppingCartEntity() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItemEntity> cartItems() {
        return cartItems;
    }

    public ShoppingCartEntity setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public double getSum() {

        double sum = 0;
        for (CartItemEntity cartItem : cartItems) {
            sum += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return sum;
    }

    public int getCountOfAllItems() {
        int count = 0;
        for (CartItemEntity cartItem : cartItems) {
            count += cartItem.getQuantity();
        }
        return count;
    }
}
