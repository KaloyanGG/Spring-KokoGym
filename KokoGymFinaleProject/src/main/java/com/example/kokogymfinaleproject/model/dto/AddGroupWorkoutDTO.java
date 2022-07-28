package com.example.kokogymfinaleproject.model.dto;

public class AddGroupWorkoutDTO {

    private String name;
    private String purpose;
    private String description;
    private String minLevel;
    private String username;

    public AddGroupWorkoutDTO() {
    }

    public String getName() {
        return name;
    }

    public AddGroupWorkoutDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public AddGroupWorkoutDTO setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddGroupWorkoutDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMinLevel() {
        return minLevel;
    }

    public AddGroupWorkoutDTO setMinLevel(String minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AddGroupWorkoutDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
