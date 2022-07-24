package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.LevelEntity;
import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    Optional<LevelEntity> findByLevel(LevelNameEnum level);

}
