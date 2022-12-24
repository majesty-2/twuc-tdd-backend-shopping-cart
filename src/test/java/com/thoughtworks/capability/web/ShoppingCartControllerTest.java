package com.thoughtworks.capability.web;

import static org.mockito.Mockito.when;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        // given
        when(shoppingCartService.findShoppingCart()).thenReturn(new ShoppingCartResponse(Collections.emptyList(), BigDecimal.ZERO));

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount").value(BigDecimal.ZERO));

    }

    @Test
    void should_return_shopping_cart_with_2_products() throws Exception {
        // given
        final var cola = new Product(1L, "cola", BigDecimal.ONE, 1);
        final var candy = new Product(2L, "candy", BigDecimal.valueOf(2), 2);
        when(shoppingCartService.findShoppingCart()).thenReturn(new ShoppingCartResponse(List.of(cola, candy), BigDecimal.valueOf(5)));

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name").value("cola"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].price").value(BigDecimal.ONE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].quantity").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].name").value("candy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].price").value(BigDecimal.valueOf(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].quantity").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount").value(BigDecimal.valueOf(5)));
    }
}
