package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "levels")
public class LevelEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private LevelNameEnum level;

    public LevelEntity() {
    }

    public LevelEntity(LevelNameEnum level) {
        this.level = level;
    }

    public LevelNameEnum getLevel() {
        return level;
    }

    public LevelEntity setLevel(LevelNameEnum level) {
        this.level = level;
        return this;
    }
}
