package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.binding.AddGroupWorkoutBindingModel;
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

import java.util.Comparator;
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
                .sorted(Comparator.comparing(GroupWorkoutDTO::getName))
                .collect(Collectors.toList());
    }

    private GroupWorkoutDTO map(GroupWorkoutEntity groupWorkoutEntity) {
        GroupWorkoutDTO groupWorkoutDTO = mapper.map(groupWorkoutEntity, GroupWorkoutDTO.class);
        groupWorkoutDTO.setTrainer(map(groupWorkoutEntity.getTrainer()));
        groupWorkoutDTO.setMinLevel(map(groupWorkoutEntity.getMinLevel()));

        return groupWorkoutDTO;
    }

    private LevelDTO map(LevelEntity levelEntity) {
        return new LevelDTO().setLevel(levelEntity.getLevel());
    }

    private TrainerDTO map(TrainerEntity trainerEntity) {
        return new TrainerDTO().setUserDTO(map(trainerEntity.getUser()));
    }

    private UserDTO map(UserEntity userEntity) {
        return new UserDTO().setFullName(userEntity.getFullName()).setImageUrl(userEntity.getImageUrl());
    }

    public void init() {

        if (this.groupWorkoutsRepository.count() != 0) {
            return;
        }
        GroupWorkoutEntity groupWorkout1=new GroupWorkoutEntity();
        groupWorkout1.setName("PowerYoga");
        groupWorkout1.setPurpose("flexibility");
        groupWorkout1.setTrainer(trainerRepository.findById(1L).get());
        groupWorkout1.setMinLevel(levelRepository.findByLevel(LevelNameEnum.BEGINNER).get());
        groupWorkout1.setDescription("This dynamic practice flows through series of poses to build strength, balance, and flexibility." +
                "Sequences change from class to class, keeping the practice fresh while still building experience in" +
                "traditional yoga poses. Throughout class, breath and movement are linked, making mindful focus a" +
                "key part of your practice.");
        this.groupWorkoutsRepository.save(groupWorkout1);

        GroupWorkoutEntity groupWorkout2 = new GroupWorkoutEntity(
                "H.I.I.T Ride",
                "Torch serious calories",
                "This cycle class has five sections -each include a heavy resistance climb with intervals and sprint" +
                        "intervals. Torch serious calories and ride to inspiring music with this high intensity class!",
                trainerRepository.findById(2L).get(),
                levelRepository.findByLevel(LevelNameEnum.ADVANCED).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout2);

        GroupWorkoutEntity groupWorkout3 = new GroupWorkoutEntity(
                "Zumba",
                "Have fun and get fit",
                "Fun filled, energetic class that involves dance and fitness elements. The choreography incorporates" +
                        "hip-hop, soca, samba, salsa, merengue and mambo. Squats and lunges are also included. ",
                trainerRepository.findById(2L).get(),
                levelRepository.findByLevel(LevelNameEnum.BEGINNER).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout3);

        GroupWorkoutEntity groupWorkout4 = new GroupWorkoutEntity(
                "BodyPump",
                "Pump",
                "For anyone looking to get lean, toned, and fit-fast. Using light to moderate weights with lots of" +
                        "repetition. This gives you a total body workout. Instructors will coach you through the scientifically" +
                        "proven moves and techniques while pumping out encouragement, motivation, and great music. You’ll" +
                        "leave the class feeling challenged, motivated, ready to come back for more.",
                trainerRepository.findById(3L).get(),
                levelRepository.findByLevel(LevelNameEnum.INTERMEDIATE).get()
        );
        this.groupWorkoutsRepository.save(groupWorkout4);

    }


    public void addGroupWorkout(AddGroupWorkoutBindingModel groupWorkoutDTO) {

        GroupWorkoutEntity groupWorkoutEntity = new GroupWorkoutEntity(
                groupWorkoutDTO.getName(),
                groupWorkoutDTO.getPurpose(),
                groupWorkoutDTO.getDescription(),
                trainerRepository.findByUserUsername(groupWorkoutDTO.getTrainerUsername()),
                levelRepository.findByLevel(groupWorkoutDTO.getMinLevel()).get()
        );
        this.groupWorkoutsRepository.save(groupWorkoutEntity);

    }


    public boolean containsGroupWorkoutByName(String name) {
        return this.groupWorkoutsRepository.findByName(name).isPresent();
    }
}










