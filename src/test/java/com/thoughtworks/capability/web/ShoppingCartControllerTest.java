package com.thoughtworks.capability.web;

import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private ShoppingCartService service;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        when(service.getShoppingCart())
                .thenReturn(new ShoppingCartResponse(emptyList(), BigDecimal.ZERO));

        mvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.products").isArray())
        .andExpect(jsonPath("$.products").isEmpty())
        .andExpect(jsonPath("$.totalPrice").value(BigDecimal.ZERO));
    }
}
