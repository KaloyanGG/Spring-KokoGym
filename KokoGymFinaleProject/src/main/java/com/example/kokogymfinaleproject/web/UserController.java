package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.dto.UpdateUserDTO;
import com.example.kokogymfinaleproject.model.entity.CustomerEntity;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/stana")
    public String stana() {
        return "uspeh";
    }


    @GetMapping("/ne_stana")
    public String neStana() {
        return "neuspeh";
    }

    @PostMapping("login-error")
    public String onFailedLogin(RedirectAttributes redirectAttributes,
                                @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/myProfile")
    public String myProfile(Model model, @AuthenticationPrincipal KokoGymUserDetails user) {

        Object customerOrTrainerById = this.userService.findCustomerOrTrainerById(user.getId());

        if (customerOrTrainerById.getClass().getName().equals("com.example.kokogymfinaleproject.model.entity.CustomerEntity")) {
            model.addAttribute("level", ((CustomerEntity) customerOrTrainerById).getLevel().getLevel().name());
        } else {
            model.addAttribute("title", ((TrainerEntity) customerOrTrainerById).getTitle());
        }

        return "myProfile";
    }

    @PostMapping("/myProfile")
    public String myProfileChangeConfirm(UpdateUserDTO updateUserDTO, @AuthenticationPrincipal KokoGymUserDetails principal) {

        this.userService.updateUser(updateUserDTO, principal.getId());

        principal.setUsername(updateUserDTO.getUsername());
        principal.setImageUrl(updateUserDTO.getImageUrl());

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, context.getAuthentication().getCredentials(), context.getAuthentication().getAuthorities());
        context.setAuthentication(authentication);

        return "redirect:myProfile";
    }

}
