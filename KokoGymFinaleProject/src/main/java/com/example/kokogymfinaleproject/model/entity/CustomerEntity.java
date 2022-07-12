package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {

    @OneToOne(optional = false)
    private UserEntity user;

    @ManyToOne
    private TrainerEntity trainer;

    @ManyToOne(optional = false)
    private Level level;

    public CustomerEntity() {
    }

    public UserEntity user() {
        return user;
    }

    public CustomerEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public TrainerEntity trainer() {
        return trainer;
    }

    public CustomerEntity setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
        return this;
    }

    public Level level() {
        return level;
    }

    public CustomerEntity setLevel(Level level) {
        this.level = level;
        return this;
    }
}
