package com.example.kokogymfinaleproject.model.binding;

import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddGroupWorkoutBindingModel {

    @NotBlank(message = "Name should be provided.")
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters.")
    private String name;
    @NotBlank(message = "Purpose should be provided.")
    @Size(min = 2, max = 40, message = "Purpose should be between 2 and 40 characters.")
    private String purpose;
    @NotBlank(message = "Description should be provided.")
    @Size(min = 2, message = "Description should be at least 2 characters.")
    private String description;
    @NotNull(message = "Min level should be provided.")
    private LevelNameEnum minLevel;
    @NotBlank(message = "Should be provided.")
    private String trainerUsername;

    public AddGroupWorkoutBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddGroupWorkoutBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public AddGroupWorkoutBindingModel setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddGroupWorkoutBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LevelNameEnum getMinLevel() {
        return minLevel;
    }

    public AddGroupWorkoutBindingModel setMinLevel(LevelNameEnum minLevel) {
        this.minLevel = minLevel;
        return this;
    }

    public String getTrainerUsername() {
        return trainerUsername;
    }

    public AddGroupWorkoutBindingModel setTrainerUsername(String trainerUsername) {
        this.trainerUsername = trainerUsername;
        return this;
    }
}
