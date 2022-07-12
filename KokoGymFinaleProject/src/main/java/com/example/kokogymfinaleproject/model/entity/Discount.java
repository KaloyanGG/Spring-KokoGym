package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.DiscountNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discounts")
public class Discount extends BaseEntity {

    @Column(unique = true, nullable = false)
    private DiscountNameEnum name;
    @Column(nullable = false, unique = true)
    private double valueInPercentage;

    public Discount() {
    }

    public DiscountNameEnum discountName() {
        return name;
    }

    public Discount setName(DiscountNameEnum discountName) {
        this.name = discountName;
        return this;
    }

    public double discountValue() {
        return valueInPercentage;
    }

    public Discount setValueInPercentage(double valueInPercentage) {
        this.valueInPercentage = valueInPercentage;
        return this;
    }
}
