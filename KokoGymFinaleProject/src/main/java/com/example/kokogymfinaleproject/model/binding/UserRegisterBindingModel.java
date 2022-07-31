package com.example.kokogymfinaleproject.model.binding;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegisterBindingModel {

    @NotBlank(message = "Username should be provided.")
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters.")
    private String username;
    @NotBlank
    @Size(min = 5, message = "Password should be at least 5 characters.")
    private String password;

    private String confirmPassword;
    @Email(message = "User email should be valid.")
    @NotBlank(message = "User email should be provided.")
    private String email;
    @NotBlank
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 characters.")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 characters.")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date should be in the past.")
    @NotNull(message = "Birth date should be provided.")
    private LocalDate birthDate;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserRegisterBindingModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
