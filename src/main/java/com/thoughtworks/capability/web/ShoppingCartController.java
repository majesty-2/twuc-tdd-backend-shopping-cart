package com.thoughtworks.capability.web;

import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @GetMapping
    public ShoppingCartResponse fetchShoppingCart() {
        return new ShoppingCartResponse(new ArrayList<>(), BigDecimal.ZERO);
    }
}
