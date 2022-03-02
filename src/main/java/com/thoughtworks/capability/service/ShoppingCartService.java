package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    public ShoppingCartService(ProductRepository productRepository) {

    }

    public ShoppingCartResponse findShoppingCart() {
        return null;
    }
}
