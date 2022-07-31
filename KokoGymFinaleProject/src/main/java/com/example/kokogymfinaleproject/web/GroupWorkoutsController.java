package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.binding.AddGroupWorkoutBindingModel;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.service.GroupWorkoutService;
import com.example.kokogymfinaleproject.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupWorkoutsController {

    private final TrainerService trainerService;
    private GroupWorkoutService groupWorkoutService;

    public GroupWorkoutsController(TrainerService trainerService, GroupWorkoutService groupWorkoutService) {
        this.trainerService = trainerService;
        this.groupWorkoutService = groupWorkoutService;
    }

    @ModelAttribute("groupWorkoutModel")
    public AddGroupWorkoutBindingModel groupWorkoutBindingModel(){
        return new AddGroupWorkoutBindingModel();
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
    public String groupWorkoutsAddConfirm(@Valid AddGroupWorkoutBindingModel groupWorkoutModel,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes){

        if(groupWorkoutService.containsGroupWorkoutByName(groupWorkoutModel.getName())){
            bindingResult.rejectValue("name", "name", "Group workout with this name already exists!");
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("groupWorkoutModel", groupWorkoutModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupWorkoutModel",
                    bindingResult);
            return "redirect:/groupWorkouts/add";
        }

        this.groupWorkoutService.addGroupWorkout(groupWorkoutModel);

        return "groupWorkouts";
    }


}









