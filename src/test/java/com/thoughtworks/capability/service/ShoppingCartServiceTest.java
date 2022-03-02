package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
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

    @Test
    public void shouldReturnShoppingCartWhenHasProducts() {
        //given
        when(productRepository.findAll()).thenReturn(Lists.list(
            new Product(1L, "p1", BigDecimal.valueOf(20), 1),
            new Product(2L, "p2", BigDecimal.valueOf(10), 2)
        ));

        //when
        ShoppingCartResponse shoppingCart = shoppingCartService.findShoppingCart();

        //then
        assertEquals(2, shoppingCart.getProducts().size());
        assertEquals(1L, shoppingCart.getProducts().get(0).getId());
        assertEquals(BigDecimal.valueOf(20), shoppingCart.getProducts().get(0).getPrice());
        assertEquals(2L, shoppingCart.getProducts().get(1).getId());
        assertEquals(BigDecimal.valueOf(10), shoppingCart.getProducts().get(1).getPrice());
        assertEquals(BigDecimal.valueOf(40), shoppingCart.getTotalAmount());
    }
}
