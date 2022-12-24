package com.thoughtworks.capability.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.List;
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

    @Test
    void should_return_shopping_cart_with_2_products() {
        // given
        final var cola = new Product(1L, "cola", BigDecimal.ONE, 1);
        final var candy = new Product(2L, "candy", BigDecimal.valueOf(2), 2);
        when(productRepository.findAll()).thenReturn(List.of(cola, candy));

        // when
        final var shoppingCart = shoppingCartService.findShoppingCart();

        // then
        assertThat(shoppingCart.getProducts()).hasSize(2);

        final var colaProduct = shoppingCart.getProducts().get(0);
        assertThat(colaProduct.getId()).isEqualTo(1L);
        assertThat(colaProduct.getName()).isEqualTo("cola");
        assertThat(colaProduct.getPrice()).isEqualTo(BigDecimal.ONE);
        assertThat(colaProduct.getQuantity()).isEqualTo(1);

        final var candyProduct = shoppingCart.getProducts().get(1);
        assertThat(candyProduct.getId()).isEqualTo(2L);
        assertThat(candyProduct.getName()).isEqualTo("candy");
        assertThat(candyProduct.getPrice()).isEqualTo(BigDecimal.valueOf(2));
        assertThat(candyProduct.getQuantity()).isEqualTo(2);

        assertThat(shoppingCart.getTotalAmount()).isEqualTo(BigDecimal.valueOf(5));

        verify(productRepository).findAll();
    }
}
