package com.example.kokogymfinaleproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {


    @GetMapping("/made")
    public String made() {
        return "orderMade";
    }

}
