package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/permission")
    public String startTrainer(@AuthenticationPrincipal KokoGymUserDetails principal){

        this.userService.makeTrainer(principal);
        principal.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_TRAINER"),
                new SimpleGrantedAuthority("ROLE_CUSTOMER")));

//        userDetails.addToAuthority(new SimpleGrantedAuthority("ROLE_TRAINER"));
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, context.getAuthentication().getCredentials(), principal.getAuthorities());
        context.setAuthentication(authentication);


        return "redirect:/users/myProfile";
    }

}
