package com.example.kokogymfinaleproject.model.dto;

public class GroupWorkoutDTO {

    private String name;
    private String purpose;
    private String description;

    private TrainerDTO trainer;

    private LevelDTO minLevel;

    public GroupWorkoutDTO() {
    }

    public String getName() {
        return name;
    }

    public GroupWorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public GroupWorkoutDTO setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public TrainerDTO getTrainer() {
        return trainer;
    }

    public GroupWorkoutDTO setTrainer(TrainerDTO trainer) {
        this.trainer = trainer;
        return this;
    }

    public LevelDTO getMinLevel() {
        return minLevel;
    }

    public GroupWorkoutDTO setMinLevel(LevelDTO minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GroupWorkoutDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
