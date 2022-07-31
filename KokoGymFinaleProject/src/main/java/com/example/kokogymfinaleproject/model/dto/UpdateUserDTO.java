package com.example.kokogymfinaleproject.model.dto;

import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDTO {

    @NotBlank
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters.")
    private String username;
    @NotBlank
    private String imageUrl;
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters.")
    private String title;
    private LevelNameEnum level;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String username, String imageUrl) {
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public UpdateUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UpdateUserDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UpdateUserDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LevelNameEnum getLevel() {
        return level;
    }

    public UpdateUserDTO setLevel(LevelNameEnum level) {
        this.level = level;
        return this;
    }
}
