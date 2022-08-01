package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<TrainerEntity> getAllTrainers() {

        return trainerRepository.findAll();

    }

    public TrainerEntity findTrainerById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }
}
