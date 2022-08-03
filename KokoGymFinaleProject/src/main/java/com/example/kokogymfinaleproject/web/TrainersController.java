package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.model.exception.TrainerNotFoundException;
import com.example.kokogymfinaleproject.service.TrainerService;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/trainers")
public class TrainersController {

    private UserService userService;
    private TrainerService trainerService;

    public TrainersController(UserService userService, TrainerService trainerService) {
        this.userService = userService;
        this.trainerService = trainerService;
    }

    @GetMapping
    public String trainers(Model model) {

        List<TrainerEntity> trainers = this.userService.findAllTrainers();
        model.addAttribute("trainers", trainers);
        return "trainers";
    }

    @GetMapping("/{id}")
    public String trainer(@PathVariable("id") Long id, Model model) throws TrainerNotFoundException {

        TrainerEntity trainer = this.trainerService.findTrainerById(id);
        if (trainer == null) {
            throw new TrainerNotFoundException(id);
        }

        model.addAttribute("trainer", trainer);

        return "trainerDetails";

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({TrainerNotFoundException.class})
    public ModelAndView onProductNotFound(TrainerNotFoundException tnfe) {

        ModelAndView modelAndView = new ModelAndView("error/trainer-not-found");
        modelAndView.addObject("trainerId", tnfe.id());

        return modelAndView;

    }

    //todo: maybe use events or paging

    @GetMapping("/permission")
    public String startTrainer(@AuthenticationPrincipal KokoGymUserDetails principal) {

        if (principal == null) {
            return "redirect:/users/login";
        }

        this.userService.makeTrainer(principal);
        principal.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_TRAINER"), new SimpleGrantedAuthority("ROLE_CUSTOMER")));

//        userDetails.addToAuthority(new SimpleGrantedAuthority("ROLE_TRAINER"));
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, context.getAuthentication().getCredentials(), principal.getAuthorities());
        context.setAuthentication(authentication);


        return "redirect:/users/myProfile";
    }

}
