package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.binding.UserRegisterBindingModel;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserRegisterBindingModel initUserModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/users/register")
    public String register() {
        return "register";
    }

    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(this.userService.findByUsername(userModel.getUsername())!=null) {
            bindingResult.rejectValue("username", "username", "Username already exists!");
        }
        if (!userModel.getPassword().equals(userModel.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword", "Password and confirm password must match!");
        }
        if(this.userService.containsEMail(userModel.getEmail())) {
            bindingResult.rejectValue("email", "email", "User with this email already exists!");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/register";
        }

        this.userService.register(userModel);

        return "redirect:/";
    }


}
