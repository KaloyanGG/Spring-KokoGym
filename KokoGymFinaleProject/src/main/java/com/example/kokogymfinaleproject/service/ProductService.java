package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.entity.ProductEntity;
import com.example.kokogymfinaleproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void init() {

        this.productRepository.saveAll(List.of(
                new ProductEntity("Whey Protein", 100, 30.99, "https://contents.mediadecathlon.com/p2118149/k$02c8b907f267ffb6eed898b2d43cf321/protein-whey-900-g-shokolad.jpg?format=auto&quality=40&f=800x800"),
                new ProductEntity("Creatine Monohidrate", 50, 50.89, "https://m.media-amazon.com/images/I/71N9eavAZPL._SY550_.jpg"),
                new ProductEntity("Fatburner", 90, 101.00, "https://hranitelnidobavki.bg/wp-content/uploads/BT-864-mega-fat-burner-90-caps-1-247x296.jpg")
        ));

    }

    public List<ProductEntity> findAll() {

        return this.productRepository.findAll();

    }
}
