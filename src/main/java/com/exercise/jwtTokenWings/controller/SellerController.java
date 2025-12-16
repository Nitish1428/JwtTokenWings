package com.exercise.jwtTokenWings.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    // Only SELLER can access
    @PostMapping("/product")
    public String addProduct() {
        return "Seller added a product";
    }

    // Only SELLER can access
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Seller deleted product with id " + id;
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @GetMapping("/discount")
    public String addDiscount() {
        return "Seller discount";
    }

}


