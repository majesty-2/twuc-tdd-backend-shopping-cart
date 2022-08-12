package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartService {
    private final ProductRepository productRepository;

    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ShoppingCartResponse getShoppingCart() {
        List<Product> products = productRepository.findAll();

        return new ShoppingCartResponse(products, BigDecimal.ZERO);
    }
}
