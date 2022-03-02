package com.thoughtworks.capability.web.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {
    private final List products;
    private final BigDecimal totalAmount;

    public ShoppingCartResponse(List products, BigDecimal totalAmount) {
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List getProducts() {
        return products;
    }
}
