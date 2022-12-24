package com.thoughtworks.capability.web.api;


import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class APIShoppingCartControllerTest {

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

    @Test
    @Sql("/sql/insert_2_products.sql")
    void should_return_shopping_cart_with_2_products() throws Exception {
        // given

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name").value("cola"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].price").value(1.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].quantity").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].name").value("candy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].price").value(2.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].quantity").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalAmount").value(BigDecimal.valueOf(5.0)));
    }
}
