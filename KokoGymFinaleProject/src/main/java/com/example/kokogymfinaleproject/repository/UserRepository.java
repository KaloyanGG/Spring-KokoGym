package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.service.KokoGymUserDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
