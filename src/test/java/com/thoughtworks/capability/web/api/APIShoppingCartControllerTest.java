package com.thoughtworks.capability.web.api;

import com.thoughtworks.capability.WebApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
}
