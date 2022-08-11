package com.example.kokogymfinaleproject.component;

import com.example.kokogymfinaleproject.model.entity.ProductEntity;
import com.example.kokogymfinaleproject.service.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Scope
@EnableScheduling
public class DiscountScheduler {

    private final ProductService productService;

    private List<ProductEntity> products;

    public DiscountScheduler(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 3600000)
    public void scheduleFixedDelayTask() {

        if(products != null) {
            for(ProductEntity product: products){
                product.setPrice(product.getPrice()*2);
                productService.save(product);
            }
        }
        this.products = new ArrayList<>();
        List<ProductEntity> all = productService.findAll();
        if (all.size() > 2) {
            Arrays.stream(giveThreeRandomNumbersBetweenWithoutRepetition(0, all.size() - 1))
                    .forEach(num -> products.add(all.get(num)));

            for (ProductEntity product : products) {
                product.setPrice(product.getPrice()/2);
                productService.save(product);
            }

            System.out.println(products);
        }

//        System.out.println(Arrays.toString(giveThreeRandomNumbersBetweenWithoutRepetition(1, 5)));


//        System.out.println("HELLOOOO");
//        Testt testt= new Testt();
//        testt.setName("test name");
//        System.out.println("Test name: -->  "+testt.getName());
//        System.out.println("-----------");
//        Testt testt2 = testt;
//        System.out.println("Test2 name: -->  "+testt2.getName());
//        testt.setName("test name change");
//        System.out.println("Test name: -->  "+testt.getName());
//        System.out.println("Test2 name: -->  "+testt2.getName());
        //Test 2 name changed when test name changed

    }

    public ProductService getProductService() {
        return productService;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public DiscountScheduler setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    private int[] giveThreeRandomNumbersBetweenWithoutRepetition(int min, int max) {
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = (int) (Math.random() * (max - min + 1)) + min;

            if (i == 1) {
                while (numbers[i] == numbers[i - 1]) {
                    numbers[i] = (int) (Math.random() * (max - min + 1)) + min;
                }
            }
            if (i == 2) {
                while (numbers[i] == numbers[i - 1] || numbers[i] == numbers[i - 2]) {
                    numbers[i] = (int) (Math.random() * (max - min + 1)) + min;
                }
            }

        }
        return numbers;
    }

}