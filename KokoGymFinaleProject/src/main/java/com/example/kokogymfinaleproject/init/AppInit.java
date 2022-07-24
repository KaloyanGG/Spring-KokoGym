package com.example.kokogymfinaleproject.init;

import com.example.kokogymfinaleproject.repository.ShoppingCartRepository;
import com.example.kokogymfinaleproject.service.LevelService;
import com.example.kokogymfinaleproject.service.RoleService;
import com.example.kokogymfinaleproject.service.ShoppingCartService;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final LevelService levelService;

    public AppInit(UserService userService, RoleService roleService, ShoppingCartRepository shoppingCartRepository, ShoppingCartService shoppingCartService, LevelService levelService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.levelService = levelService;
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.init();
        shoppingCartService.init();
        levelService.init();
        userService.init();
    }
}
