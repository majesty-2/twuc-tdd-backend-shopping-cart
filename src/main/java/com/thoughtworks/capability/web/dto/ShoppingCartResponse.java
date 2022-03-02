package com.thoughtworks.capability.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Data
public class ShoppingCartResponse {
    private final List products;
    private final BigDecimal totalAmount;
}
