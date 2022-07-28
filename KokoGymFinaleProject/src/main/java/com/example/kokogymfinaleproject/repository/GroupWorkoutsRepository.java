package com.example.kokogymfinaleproject.repository;

import com.example.kokogymfinaleproject.model.entity.GroupWorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWorkoutsRepository extends JpaRepository<GroupWorkoutEntity, Long> {


}
