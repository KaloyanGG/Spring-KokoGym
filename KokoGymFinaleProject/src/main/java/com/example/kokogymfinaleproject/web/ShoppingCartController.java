package com.example.kokogymfinaleproject.web;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.entity.OrderEntity;
import com.example.kokogymfinaleproject.service.OrderService;
import com.example.kokogymfinaleproject.service.ProductService;
import com.example.kokogymfinaleproject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    private UserService userService;
    private OrderService orderService;
    private ProductService productService;

    public ShoppingCartController(UserService userService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping()
    public String shoppingCart(Model model, @AuthenticationPrincipal KokoGymUserDetails userDetails) {

        model.addAttribute("sum", userDetails.getShoppingCart().getSum());
        model.addAttribute("count", userDetails.getShoppingCart().getCountOfAllItems());

        return "shoppingCart";
    }

    @GetMapping("/increase/{id}")
    public String increase(@PathVariable("id") Long id, @AuthenticationPrincipal KokoGymUserDetails userDetails) {
        userService.increaseQuantityInShoppingCart(id, userDetails);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable("id") Long id, @AuthenticationPrincipal KokoGymUserDetails userDetails) {
        userService.decreaseQuantityInShoppingCart(id, userDetails);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, @AuthenticationPrincipal KokoGymUserDetails userDetails) {
        userService.removeFromShoppingCart(id, userDetails);
        return "redirect:/shoppingCart";
    }

    @GetMapping("/checkout")
    public String makeOrder(Model model, @AuthenticationPrincipal KokoGymUserDetails userDetails) {
        OrderEntity order = orderService.makeOrder(userDetails);
        productService.reduceQuantity(order);
        model.addAttribute("order", order);
        return "orderMade";
    }



}
