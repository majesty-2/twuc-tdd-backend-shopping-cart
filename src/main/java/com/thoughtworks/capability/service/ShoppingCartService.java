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
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Product product : products) {
            totalAmount = totalAmount.add(
                product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())
                ));
        }
        return new ShoppingCartResponse(products, totalAmount);
    }
}
