package com.example.kokogymfinaleproject.model.dto;

public class TrainerDTO {

    private UserDTO userDTO;

    public TrainerDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public TrainerDTO setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }
}
