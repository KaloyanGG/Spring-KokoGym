package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.binding.UserRegisterDTO;
import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.repository.RoleRepository;
import com.example.kokogymfinaleproject.repository.ShoppingCartRepository;
import com.example.kokogymfinaleproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper;
    private RoleRepository roleRepository;
    private ShoppingCartRepository shoppingCartRepository;

    public UserService(UserRepository userRepository, ModelMapper mapper, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = this.mapper.map(userRegisterDTO, UserEntity.class);
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        this.shoppingCartRepository.save(shoppingCart);
        user.setShoppingCart(shoppingCart);
        user.setRoles(List.of(this.roleRepository.findById(1L).get()));

        this.userRepository.save(user);
    }

}
