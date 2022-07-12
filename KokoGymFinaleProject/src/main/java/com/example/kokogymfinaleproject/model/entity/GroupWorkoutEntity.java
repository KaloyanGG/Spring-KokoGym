package com.example.kokogymfinaleproject.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "group_workouts")
public class GroupWorkoutEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String purpose;
    @ManyToOne(optional = false)
    private TrainerEntity trainer;

    @ManyToOne(optional = false)
    @Enumerated(EnumType.STRING)
    private Level minLevel;

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public GroupWorkoutEntity() {
    }

    public String name() {
        return name;
    }

    public GroupWorkoutEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String purpose() {
        return purpose;
    }

    public GroupWorkoutEntity setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public TrainerEntity trainer() {
        return trainer;
    }

    public GroupWorkoutEntity setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
        return this;
    }

    public Level minLevel() {
        return minLevel;
    }

    public GroupWorkoutEntity setMinLevel(Level minLevel) {
        this.minLevel = minLevel;
        return this;
    }
}
