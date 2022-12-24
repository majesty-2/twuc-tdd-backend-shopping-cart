package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ProductRepository productRepository;

    public ShoppingCartResponse findShoppingCart() {
        return new ShoppingCartResponse(productRepository.findAll(), BigDecimal.ZERO);
    }
}
