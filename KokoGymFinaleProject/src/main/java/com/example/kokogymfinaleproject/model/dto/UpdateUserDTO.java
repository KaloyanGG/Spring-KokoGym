package com.example.kokogymfinaleproject.model.dto;

public class UpdateUserDTO {

    private String username;
    private String imageUrl;
    private String title;
    private String level;

    public UpdateUserDTO() {
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

    public String getLevel() {
        return level;
    }

    public UpdateUserDTO setLevel(String level) {
        this.level = level;
        return this;
    }
}
