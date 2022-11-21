package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.dto.GroupWorkoutDTO;
import com.example.kokogymfinaleproject.service.GroupWorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class GroupWorkoutsApiController {

    private final GroupWorkoutService groupWorkoutService;

    public GroupWorkoutsApiController(GroupWorkoutService groupWorkoutService) {
        this.groupWorkoutService = groupWorkoutService;
    }

    @GetMapping("/groupWorkouts/all")
    public ResponseEntity<List<GroupWorkoutDTO>> groupWorkouts(){
        List<GroupWorkoutDTO> groupWorkouts = groupWorkoutService.getAllGroupWorkouts();
        return ResponseEntity.ok(groupWorkouts);
    }

    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "Everything looks healthy :)";
    }


}
