package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ProductRepository productRepository;

    public ShoppingCartResponse findShoppingCart() {

        final var products = productRepository.findAll();
        final var totalAmount = products.stream()
                .map(Product::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ShoppingCartResponse(products, totalAmount);
    }
}
