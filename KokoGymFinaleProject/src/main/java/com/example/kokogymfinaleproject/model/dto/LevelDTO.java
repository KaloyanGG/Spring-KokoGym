package com.example.kokogymfinaleproject.model.dto;

import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;

public class LevelDTO {

    private LevelNameEnum level;

    public LevelDTO() {
    }

    public LevelNameEnum getLevel() {
        return level;
    }

    public LevelDTO setLevel(LevelNameEnum level) {
        this.level = level;
        return this;
    }
}
