package com.thoughtworks.capability.web;

import static org.mockito.Mockito.when;

import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import java.math.BigDecimal;
import java.util.Collections;
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
}
