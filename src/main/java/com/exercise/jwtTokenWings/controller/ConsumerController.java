package com.exercise.jwtTokenWings.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    // Only CONSUMER can access
    @GetMapping("/products")
    public String viewProducts() {
        return "Consumer can view products";
    }

    // Only CONSUMER can access
    @PostMapping("/cart")
    public String addToCart() {
        return "Product added to cart";
    }

    @PreAuthorize("hasAuthority('CONSUMER')")
    @GetMapping("/orders")
    public String myOrders() {
        return "Consumer orders";
    }

    @PreAuthorize("hasAuthority('CONSUMER')")
    @GetMapping(value = "/products", params = "productName")
    public String viewProductsByProductName(@RequestParam String productName) {
        return "Consumer can view products: "+ productName;
    }

}
