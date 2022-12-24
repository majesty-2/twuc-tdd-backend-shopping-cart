package com.thoughtworks.capability.web;

import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public ShoppingCartResponse fetchShoppingCart() {
        return shoppingCartService.findShoppingCart();
    }

}
