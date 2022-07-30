package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.CartItemEntity;
import com.example.kokogymfinaleproject.model.entity.OrderEntity;
import com.example.kokogymfinaleproject.model.entity.OrderItemEntity;
import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import com.example.kokogymfinaleproject.repository.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemsRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemsRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    public OrderEntity makeOrder(KokoGymUserDetails userDetails) {

        ShoppingCartEntity loggedUserShoppingCart = userDetails.getShoppingCart();
        OrderEntity order = new OrderEntity();
        order.setUser(this.userRepository.findById(userDetails.getId()).get());
        this.orderRepository.save(order);
        for (CartItemEntity cartItem : loggedUserShoppingCart.getCartItems()) {

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());

            this.orderItemRepository.save(orderItem);
            order.getOrderItems().add(orderItem);

        }

        this.cartItemsRepository.deleteAll(loggedUserShoppingCart.getCartItems());
        loggedUserShoppingCart.getCartItems().clear();
        this.shoppingCartRepository.save(loggedUserShoppingCart);

        return orderRepository.save(order);

    }
}
