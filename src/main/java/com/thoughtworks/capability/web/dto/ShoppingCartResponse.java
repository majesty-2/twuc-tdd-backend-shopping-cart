package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {
    private final List<Product> products;
    private final BigDecimal totalPrice;

    public ShoppingCartResponse(List<Product> products, BigDecimal totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
