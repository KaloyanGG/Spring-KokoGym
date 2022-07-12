package com.example.kokogymfinaleproject.init;

import com.example.kokogymfinaleproject.service.RoleService;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;;

    public AppInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.init();
    }
}
