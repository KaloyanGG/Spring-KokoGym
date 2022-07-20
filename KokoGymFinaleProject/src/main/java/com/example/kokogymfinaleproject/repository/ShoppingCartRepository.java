package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

}
