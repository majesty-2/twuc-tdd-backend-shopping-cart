package com.thoughtworks.capability.web.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {
    private final List<Object> products;
    private final BigDecimal totalPrice;

    public ShoppingCartResponse(List<Object> products, BigDecimal totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public List<Object> getProducts() {
        return products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
