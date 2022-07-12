package com.example.kokogymfinaleproject.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainers")
public class TrainerEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "trainer")
    private List<CustomerEntity> customers;
    @OneToMany(mappedBy = "trainer")
    private List<GroupWorkoutEntity> groupWorkouts;

    public TrainerEntity() {
    }

    public Long userId() {
        return userId;
    }

    public TrainerEntity setUserId(Long userId) {
        this.userId = userId;
        return this;
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
}
