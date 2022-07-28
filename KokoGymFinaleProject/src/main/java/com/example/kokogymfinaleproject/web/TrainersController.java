package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trainers")
public class TrainersController {

    private UserService userService;

    public TrainersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String trainers(Model model) {

        List<TrainerEntity> trainers = this.userService.findAllTrainers();

        model.addAttribute("trainers", trainers);

        return "trainers";

    }

}
