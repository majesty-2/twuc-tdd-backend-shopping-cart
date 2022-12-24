package com.thoughtworks.capability.web.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {
    private final List products;
    private final BigDecimal totalAmount;

    public ShoppingCartResponse(final List products, final BigDecimal totalAmount) {
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public List getProducts() {
        return products;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
