package com.thoughtworks.capability.web.api;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class APIShoppingCartControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void should_return_empty_shopping_cart() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products").isEmpty())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.ZERO));
    }

    @Test
    @Sql("/sql/insert_2_products.sql")
    void should_return_shopping_cart_with_2_products() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/shopping-cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products[0].title").value("cola"))
                .andExpect(jsonPath("$.products[0].price").value(1.0))
                .andExpect(jsonPath("$.products[0].quantity").value(1))
                .andExpect(jsonPath("$.products[1].title").value("candy"))
                .andExpect(jsonPath("$.products[1].price").value(10.0))
                .andExpect(jsonPath("$.products[1].quantity").value(2))
                .andExpect(jsonPath("$.totalPrice").value(21.0));
    }
}



