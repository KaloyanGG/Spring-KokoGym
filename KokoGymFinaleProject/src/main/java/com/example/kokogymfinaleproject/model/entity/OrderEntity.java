package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @OneToMany(mappedBy = "order")
    List<OrderItemEntity> orderItems;

    @ManyToOne(optional = false)
    private UserEntity user;

//    @ManyToOne(optional = false)
//    private DiscountEntity discountEntity;

    public OrderEntity() {
        orderItems = new ArrayList<>();
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public OrderEntity setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public OrderEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItemEntity orderItem : orderItems) {
            totalPrice += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }

//    public DiscountEntity getDiscountEntity() {
//        return discountEntity;
//    }
//
//    public OrderEntity setDiscountEntity(DiscountEntity discountEntity) {
//        this.discountEntity = discountEntity;
//        return this;
//    }
}
