package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
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

    @Test
    void should_return_shopping_cart_with_2_products() {
        Product cola = new Product("cola", BigDecimal.ONE, 1);
        Product candy = new Product("candy", BigDecimal.TEN, 2);

        when(productRepository.findAll()).thenReturn(asList(cola, candy));

        ShoppingCartResponse shoppingCart = service.getShoppingCart();
        
        assertEquals(2, shoppingCart.getProducts().size());
        
        Product colaProduct = shoppingCart.getProducts().get(0);
        assertEquals("cola", colaProduct.getTitle());
        assertEquals(BigDecimal.ONE, colaProduct.getPrice());
        assertEquals(1, colaProduct.getQuantity());

        Product candyProduct = shoppingCart.getProducts().get(1);
        assertEquals("candy", candyProduct.getTitle());
        assertEquals(BigDecimal.TEN, candyProduct.getPrice());
        assertEquals(2, candyProduct.getQuantity());

        assertEquals(BigDecimal.valueOf(21), shoppingCart.getTotalPrice());
        
    }
}




























