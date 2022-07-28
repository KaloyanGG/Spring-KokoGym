package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.DiscountNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discounts")
public class DiscountEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private DiscountNameEnum name;
    @Column(nullable = false, unique = true)
    private double valueInPercentage;

    public DiscountEntity() {
    }

    public DiscountNameEnum discountName() {
        return name;
    }

    public DiscountEntity setName(DiscountNameEnum discountName) {
        this.name = discountName;
        return this;
    }

    public double discountValue() {
        return valueInPercentage;
    }

    public DiscountEntity setValueInPercentage(double valueInPercentage) {
        this.valueInPercentage = valueInPercentage;
        return this;
    }
}
