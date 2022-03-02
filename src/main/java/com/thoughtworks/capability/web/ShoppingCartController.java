package com.thoughtworks.capability.web;

import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse findShoppingCart() {
        return shoppingCartService.findShoppingCart();
    }
}
