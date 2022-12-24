package com.thoughtworks.capability.service;

import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    public ShoppingCartResponse findShoppingCart() {
        return new ShoppingCartResponse(new ArrayList<>(), BigDecimal.ZERO);
    }
}
