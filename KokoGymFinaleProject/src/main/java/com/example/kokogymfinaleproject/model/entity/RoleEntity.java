package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public RoleEntity(RoleEnum name) {
        this.name = name;
    }

    public RoleEntity() {
    }

    public RoleEnum role() {
        return name;
    }

    public RoleEntity setName(RoleEnum name) {
        this.name = name;
        return this;
    }
}
