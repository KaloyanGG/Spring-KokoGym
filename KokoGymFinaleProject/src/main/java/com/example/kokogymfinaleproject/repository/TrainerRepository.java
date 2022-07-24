package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {


    Optional<TrainerEntity> findByUserId(Long id);
}
