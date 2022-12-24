package com.thoughtworks.capability.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thoughtworks.capability.repository.ProductRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Test
    void should_return_empty_shopping_cart() {
        // given
        when(productRepository.findAll()).thenReturn(emptyList());

        // when
        final var shoppingCart = shoppingCartService.findShoppingCart();

        // then
        assertThat(shoppingCart.getProducts()).isEmpty();
        assertThat(shoppingCart.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        verify(productRepository).findAll();
    }
}
