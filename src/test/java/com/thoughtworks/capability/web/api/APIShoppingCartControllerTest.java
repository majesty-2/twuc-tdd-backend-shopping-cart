package com.thoughtworks.capability.web.api;

import com.thoughtworks.capability.WebApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class APIShoppingCartControllerTest extends WebApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnEmptyShoppingCartWhenHasNotProduct() throws Exception {
        //given
        //when
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCart"))
            //then
            .andExpect(jsonPath("$.products").isArray())
            .andExpect(jsonPath("$.totalAmount").value(BigDecimal.ZERO));

    }

    @Test
    @Sql("/sql/insert_2_products.sql")
    public void shouldReturnShoppingCartWhenHasProducts() throws Exception {
        //given
        //when
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCart"))
            //then
            .andExpect(jsonPath("$.products").isArray())
            .andExpect(jsonPath("$.products.[0].id").value(1L))
            .andExpect(jsonPath("$.products.[0].price").value(BigDecimal.valueOf(20).setScale(1)))
            .andExpect(jsonPath("$.products.[1].id").value(2L))
            .andExpect(jsonPath("$.products.[1].price").value(BigDecimal.valueOf(10).setScale(1)))
            .andExpect(jsonPath("$.totalAmount").value(BigDecimal.valueOf(40).setScale(1)));
    }
}
