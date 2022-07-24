package com.example.kokogymfinaleproject.model.entity;

import com.example.kokogymfinaleproject.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public RoleEntity(RoleEnum role) {
        this.role = role;
    }

    public RoleEntity() {
    }

    public RoleEnum role() {
        return role;
    }

    public RoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public RoleEnum getRole() {
        return role;
    }
}
