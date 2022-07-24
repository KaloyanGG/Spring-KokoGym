package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import com.example.kokogymfinaleproject.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void init() {
        if(this.shoppingCartRepository.count() == 0) {
            ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
            this.shoppingCartRepository.save(shoppingCart);
        }
    }
}
