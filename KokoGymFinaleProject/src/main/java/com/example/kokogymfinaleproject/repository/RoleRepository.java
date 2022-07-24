package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.RoleEntity;
import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


    RoleEntity findByRole(RoleEnum role);
}
