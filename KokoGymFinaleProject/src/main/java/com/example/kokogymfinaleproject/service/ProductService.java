package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.binding.AddProductBindingModel;
import com.example.kokogymfinaleproject.model.entity.*;
import com.example.kokogymfinaleproject.repository.CartItemRepository;
import com.example.kokogymfinaleproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    public ProductService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void init() {

        this.productRepository.saveAll(List.of(
                new ProductEntity("Whey Protein", 5, 30.99, "https://bit.ly/3Jq85JR"),
                new ProductEntity("Creatine Monohidrate", 50, 50.89, "https://bit.ly/3QiliGN"),
                new ProductEntity("Fatburner", 90, 101.00, "https://bit.ly/3PVKmn0"),
                new ProductEntity("BCAA", 12, 200.00, "https://bit.ly/3zTv4tu"),
                new ProductEntity("Trenbolone Acetate", 20, 50.00, "https://bit.ly/3Sp0dfo")
        ));

    }

    public List<ProductEntity> findAll() {
        return this.productRepository.findAll();
    }

    public void add(AddProductBindingModel productDTO) {
        ProductEntity product = new ProductEntity(
                productDTO.getName(),
                productDTO.getStockQuantity(),
                productDTO.getPrice(),
                productDTO.getImageUrl()
        );
        this.productRepository.save(product);
    }

    public void makeCartItem(Long id, ShoppingCartEntity shoppingCart) {

        ProductEntity productEntity = this.productRepository.findById(id).orElse(null);
        CartItemEntity cartItemEntity = new CartItemEntity()
                .setProduct(productEntity)
                .setQuantity(1)
                .setShoppingCart(shoppingCart);
        CartItemEntity forSaving = this.cartItemRepository.save(cartItemEntity);
        shoppingCart.getCartItems().add(forSaving);


    }

    public void reduceQuantity(OrderEntity order) {

        List<OrderItemEntity> itemsInOrder = order.getOrderItems();

        for (OrderItemEntity orderItem : itemsInOrder) {
            ProductEntity productEntity = this.productRepository.findById(orderItem.getProduct().getId()).get();
            productEntity.setStockQuantity(productEntity.getStockQuantity() - orderItem.getQuantity());
            this.productRepository.save(productEntity);
        }
    }
}
