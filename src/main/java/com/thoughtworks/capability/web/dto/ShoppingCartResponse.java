package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ShoppingCartResponse {
    private final List<Product> products;
    private final BigDecimal totalAmount;

}
