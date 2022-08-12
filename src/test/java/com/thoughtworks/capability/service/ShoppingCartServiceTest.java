package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceTest {
    private ShoppingCartService service;
    private final ProductRepository productRepository = mock(ProductRepository.class);

    @BeforeEach
    void setUp() {
        service = new ShoppingCartService(productRepository);
    }

    @Test
    void should_return_empty_shopping_cart() {
        when(productRepository.findAll()).thenReturn(emptyList());

        ShoppingCartResponse shoppingCart = service.getShoppingCart();

        assertEquals(0, shoppingCart.getProducts().size());
        assertEquals(BigDecimal.ZERO, shoppingCart.getTotalPrice());
    }
}






