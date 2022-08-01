package com.example.kokogymfinaleproject.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TrainerNotFoundException extends Throwable {

    private final Long id;

    public TrainerNotFoundException(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
