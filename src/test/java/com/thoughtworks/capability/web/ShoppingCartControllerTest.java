package com.thoughtworks.capability.web;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount").value(BigDecimal.ZERO));

    }
}
