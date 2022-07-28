package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.dto.AddGroupWorkoutDTO;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.service.GroupWorkoutService;
import com.example.kokogymfinaleproject.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GroupWorkoutsController {

    private final TrainerService trainerService;
    private GroupWorkoutService groupWorkoutService;

    public GroupWorkoutsController(TrainerService trainerService, GroupWorkoutService groupWorkoutService) {
        this.trainerService = trainerService;
        this.groupWorkoutService = groupWorkoutService;
    }

    @GetMapping("/groupWorkouts")
    public String groupWorkouts(){
        return "groupWorkouts";
    }

    @GetMapping("/groupWorkouts/add")
    public String addGroupWorkouts(Model model){

        List<TrainerEntity> trainers = this.trainerService.getAllTrainers();
        model.addAttribute("trainers", trainers);

        return "addGroupWorkout";
    }

    @PostMapping("/groupWorkouts/add")
    public String groupWorkoutsAddConfirm(AddGroupWorkoutDTO groupWorkoutDTO){

        this.groupWorkoutService.addGroupWorkout(groupWorkoutDTO);

        return "groupWorkouts";
    }

}









