package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.dto.*;
import com.example.kokogymfinaleproject.model.entity.GroupWorkoutEntity;
import com.example.kokogymfinaleproject.model.entity.LevelEntity;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import com.example.kokogymfinaleproject.repository.GroupWorkoutsRepository;
import com.example.kokogymfinaleproject.repository.LevelRepository;
import com.example.kokogymfinaleproject.repository.TrainerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupWorkoutService {

    private final GroupWorkoutsRepository groupWorkoutsRepository;
    private final TrainerRepository trainerRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper mapper;


    public GroupWorkoutService(GroupWorkoutsRepository groupWorkoutsRepository, TrainerRepository trainerRepository, LevelRepository levelRepository, ModelMapper mapper) {
        this.groupWorkoutsRepository = groupWorkoutsRepository;
        this.trainerRepository = trainerRepository;
        this.levelRepository = levelRepository;
        this.mapper = mapper;
    }

    public List<GroupWorkoutDTO> getAllGroupWorkouts() {

        List<GroupWorkoutEntity> all = this.groupWorkoutsRepository.findAll();

        return groupWorkoutsRepository
                .findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
    private GroupWorkoutDTO map(GroupWorkoutEntity groupWorkoutEntity) {
        GroupWorkoutDTO groupWorkoutDTO = mapper.map(groupWorkoutEntity, GroupWorkoutDTO.class);
        groupWorkoutDTO.setTrainer(map(groupWorkoutEntity.getTrainer()));
        groupWorkoutDTO.setMinLevel(map(groupWorkoutEntity.getMinLevel()));

        return groupWorkoutDTO;
    }
    private LevelDTO map(LevelEntity levelEntity){
        return new LevelDTO().setLevel(levelEntity.getLevel());
    }

    private TrainerDTO map(TrainerEntity trainerEntity) {
        return new TrainerDTO().setUserDTO(map(trainerEntity.getUser()));
    }

    private UserDTO map(UserEntity userEntity) {
        return new UserDTO().setFullName(userEntity.getFullName()).setImageUrl(userEntity.getImageUrl());
    }

    public void init() {

        GroupWorkoutEntity groupWorkout1 = new GroupWorkoutEntity(
                "GroupWorkout",
                "WeightLoss",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae mi eu risus semper elementum facilisis a est. Donec elementum dignissim bibendum. In efficitur pharetra auctor. Nulla congue, sem quis tempor faucibus, ante nunc molestie dui, at iaculis nulla est sed eros. Sed sagittis non ante ut rutrum. Maecenas lobortis purus id risus fermentum dictum. Morbi ex risus, ullamcorper rhoncus massa at, finibus commodo libero. Praesent vitae mi nulla. Nulla congue auctor leo, eget laoreet dui mattis nec. In eleifend at lacus a tristique. Maecenas scelerisque eu lectus sit amet convallis. Nulla interdum condimentum purus vel tincidunt. Nullam non sem.",
                trainerRepository.findById(1L).get(),
                levelRepository.findByLevel(LevelNameEnum.BEGINNER).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout1);

        GroupWorkoutEntity groupWorkout2 = new GroupWorkoutEntity(
                "GroupWorkout2",
                "MuscleGain",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae mi eu risus semper elementum facilisis a est. Donec elementum dignissim bibendum. In efficitur pharetra auctor. Nulla congue, sem quis tempor faucibus, ante nunc molestie dui, at iaculis nulla est sed eros. Sed sagittis non ante ut rutrum. Maecenas lobortis purus id risus fermentum dictum. Morbi ex risus, ullamcorper rhoncus massa at, finibus commodo libero. Praesent vitae mi nulla. Nulla congue auctor leo, eget laoreet dui mattis nec. In eleifend at lacus a tristique. Maecenas scelerisque eu lectus sit amet convallis. Nulla interdum condimentum purus vel tincidunt. Nullam non sem.",
                trainerRepository.findById(2L).get(),
                levelRepository.findByLevel(LevelNameEnum.ADVANCED).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout2);

        GroupWorkoutEntity groupWorkout3 = new GroupWorkoutEntity(
                "GroupWorkout3",
                "CardioImprovement",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae mi eu risus semper elementum facilisis a est. Donec elementum dignissim bibendum. In efficitur pharetra auctor. Nulla congue, sem quis tempor faucibus, ante nunc molestie dui, at iaculis nulla est sed eros. Sed sagittis non ante ut rutrum. Maecenas lobortis purus id risus fermentum dictum. Morbi ex risus, ullamcorper rhoncus massa at, finibus commodo libero. Praesent vitae mi nulla. Nulla congue auctor leo, eget laoreet dui mattis nec. In eleifend at lacus a tristique. Maecenas scelerisque eu lectus sit amet convallis. Nulla interdum condimentum purus vel tincidunt. Nullam non sem.",
                trainerRepository.findById(2L).get(),
                levelRepository.findByLevel(LevelNameEnum.BEGINNER).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout3);

        GroupWorkoutEntity groupWorkout4 = new GroupWorkoutEntity(
                "GroupWorkout4",
                "SomeGroupWorkout",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vitae mi eu risus semper elementum facilisis a est. Donec elementum dignissim bibendum. In efficitur pharetra auctor. Nulla congue, sem quis tempor faucibus, ante nunc molestie dui, at iaculis nulla est sed eros. Sed sagittis non ante ut rutrum. Maecenas lobortis purus id risus fermentum dictum. Morbi ex risus, ullamcorper rhoncus massa at, finibus commodo libero. Praesent vitae mi nulla. Nulla congue auctor leo, eget laoreet dui mattis nec. In eleifend at lacus a tristique. Maecenas scelerisque eu lectus sit amet convallis. Nulla interdum condimentum purus vel tincidunt. Nullam non sem.",
                trainerRepository.findById(3L).get(),
                levelRepository.findByLevel(LevelNameEnum.INTERMEDIATE).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout4);

    }


    public void addGroupWorkout(AddGroupWorkoutDTO groupWorkoutDTO) {

        GroupWorkoutEntity groupWorkoutEntity = new GroupWorkoutEntity(
                groupWorkoutDTO.getName(),
                groupWorkoutDTO.getPurpose(),
                groupWorkoutDTO.getDescription(),
                trainerRepository.findByUserUsername(groupWorkoutDTO.getUsername()),
                levelRepository.findByLevel(LevelNameEnum.valueOf(groupWorkoutDTO.getMinLevel())).get()
        );
        this.groupWorkoutsRepository.save(groupWorkoutEntity);

    }
}










