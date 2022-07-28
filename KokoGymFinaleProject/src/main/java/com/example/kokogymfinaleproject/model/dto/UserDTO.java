package com.example.kokogymfinaleproject.model.dto;

public class UserDTO {

    private String fullName;
    private String imageUrl;

    public UserDTO() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
