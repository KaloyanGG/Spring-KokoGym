package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "levels")
public class Level extends BaseEntity {

    @Column(unique = true, nullable = false)
    private LevelNameEnum level;


    public Level() {
    }

    public LevelNameEnum level() {
        return level;
    }

    public Level setLevel(LevelNameEnum level) {
        this.level = level;
        return this;
    }
}
