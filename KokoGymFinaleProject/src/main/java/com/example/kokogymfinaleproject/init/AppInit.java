package com.example.kokogymfinaleproject.init;

import com.example.kokogymfinaleproject.repository.ShoppingCartRepository;
import com.example.kokogymfinaleproject.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;
    private final LevelService levelService;
    private final ProductService productService;
    private final GroupWorkoutService groupWorkoutService;

    public AppInit(UserService userService, RoleService roleService, ShoppingCartRepository shoppingCartRepository, ShoppingCartService shoppingCartService, LevelService levelService, ProductService productService, GroupWorkoutService groupWorkoutService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
        this.levelService = levelService;
        this.productService = productService;
        this.groupWorkoutService = groupWorkoutService;
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.init();
        shoppingCartService.init();
        levelService.init();
        userService.init();
        productService.init();
        groupWorkoutService.init();

    }
}
