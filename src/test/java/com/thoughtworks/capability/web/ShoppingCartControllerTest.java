package com.thoughtworks.capability.web;


import com.thoughtworks.capability.domain.Product;
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

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShoppingCartService service;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        when(service.getShoppingCart()).thenReturn(new ShoppingCartResponse(emptyList(), BigDecimal.ZERO));

        mvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products").isEmpty())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.ZERO));
    }

    @Test
    void should_return_shopping_cart_with_2_products() throws Exception {
        Product cola = new Product(1L, "cola", BigDecimal.ONE, 1);
        Product candy = new Product(2L, "candy", BigDecimal.TEN, 2);
        when(service.getShoppingCart())
                .thenReturn(new ShoppingCartResponse(asList(cola, candy), BigDecimal.valueOf(21)));

        mvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products[0].id").value(1L))
                .andExpect(jsonPath("$.products[0].title").value("cola"))
                .andExpect(jsonPath("$.products[0].price").value(BigDecimal.ONE))
                .andExpect(jsonPath("$.products[0].quantity").value(1))
                .andExpect(jsonPath("$.products[1].id").value(2L))
                .andExpect(jsonPath("$.products[1].title").value("candy"))
                .andExpect(jsonPath("$.products[1].price").value(BigDecimal.TEN))
                .andExpect(jsonPath("$.products[1].quantity").value(2))
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.valueOf(21)))
        ;
    }
}
























