package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

    Optional<TrainerEntity> findByUserId(Long id);
// todo: change to username
//    @Query("select t from TrainerEntity t where t.user.username = :username")
//    List<TrainerEntity> findByFullName(String username);

    TrainerEntity findByUserUsername(String username);
}
