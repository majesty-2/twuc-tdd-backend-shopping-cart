package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartServiceTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ShoppingCartService shoppingCartService = new ShoppingCartService(productRepository);

    @Test
    public void shouldReturnEmptyShoppingCartWhenHasNotProducts() {
        //given
        when(productRepository.findAll()).thenReturn(Lists.emptyList());

        //when
        ShoppingCartResponse shoppingCart = shoppingCartService.findShoppingCart();

        //then
        assertEquals(shoppingCart.getProducts().size(), 0);
        assertEquals(shoppingCart.getTotalAmount(), BigDecimal.ZERO);
    }
}
