package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainers")
public class TrainerEntity extends BaseEntity {

    @OneToOne(optional = false)
    private UserEntity user;
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "trainer")
    private List<CustomerEntity> customers;
    @OneToMany(mappedBy = "trainer")
    private List<GroupWorkoutEntity> groupWorkouts;

    public TrainerEntity() {
        this.customers = new ArrayList<>();
        this.groupWorkouts = new ArrayList<>();
    }

    public TrainerEntity(UserEntity user, String title) {
        this.user = user;
        this.title = title;
        this.customers=new ArrayList<>();
        this.groupWorkouts=new ArrayList<>();
    }

    public List<CustomerEntity> customers() {
        return customers;
    }

    public TrainerEntity setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
        return this;
    }

    public String title() {
        return title;
    }

    public TrainerEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<GroupWorkoutEntity> groupWorkouts() {
        return groupWorkouts;
    }

    public TrainerEntity setGroupWorkouts(List<GroupWorkoutEntity> groupWorkouts) {
        this.groupWorkouts = groupWorkouts;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TrainerEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public List<GroupWorkoutEntity> getGroupWorkouts() {
        return groupWorkouts;
    }
}
