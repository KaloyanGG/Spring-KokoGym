package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.binding.UserRegisterDTO;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserRegisterDTO initUserModel() {
        return new UserRegisterDTO();
    }

    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    @PostMapping("/users/register")
    public String registerConfirm(UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/register";
        }

        this.userService.register(userRegisterDTO);

        return "redirect:/";
    }






}
