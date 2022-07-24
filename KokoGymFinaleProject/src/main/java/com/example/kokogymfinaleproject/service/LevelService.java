package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.entity.LevelEntity;
import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import com.example.kokogymfinaleproject.repository.LevelRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public void init() {

        if(levelRepository.count() == 0){
            levelRepository.save(new LevelEntity(LevelNameEnum.BEGINNER));
            levelRepository.save(new LevelEntity(LevelNameEnum.INTERMEDIATE));
            levelRepository.save(new LevelEntity(LevelNameEnum.ADVANCED));
        }

    }
}
