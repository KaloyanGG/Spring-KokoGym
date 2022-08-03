package com.example.kokogymfinaleproject.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "group_workouts")
public class GroupWorkoutEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String purpose;
    @Column(nullable = false, length = 100000)
    @Lob
    private String description;

    @ManyToOne(optional = false)
    private TrainerEntity trainer;

    @ManyToOne(optional = false)
    @Enumerated(EnumType.STRING)
    private LevelEntity minLevel;


    public TrainerEntity getTrainer() {
        return trainer;
    }

    public GroupWorkoutEntity() {
    }

    public GroupWorkoutEntity(String name, String purpose, String description, TrainerEntity trainer, LevelEntity minLevel) {
        this.name = name;
        this.purpose = purpose;
        this.description = description;
        this.trainer = trainer;
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public GroupWorkoutEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public GroupWorkoutEntity setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public GroupWorkoutEntity setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
        return this;
    }

    public LevelEntity getMinLevel() {
        return minLevel;
    }

    public GroupWorkoutEntity setMinLevel(LevelEntity minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GroupWorkoutEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
