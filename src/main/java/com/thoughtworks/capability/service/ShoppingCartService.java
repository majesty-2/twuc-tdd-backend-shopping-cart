package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductRepository productRepository;

    public ShoppingCartResponse findShoppingCart() {
        List<Product> products = productRepository.findAll();
        BigDecimal totalAmount = products.stream()
            .map(Product::getPriceTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ShoppingCartResponse(products, totalAmount);
    }

}
