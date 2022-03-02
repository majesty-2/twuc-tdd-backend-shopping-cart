package com.thoughtworks.capability.web;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ShoppingCartControllerTest extends WebApplicationTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ShoppingCartService shoppingCartService;

    @Test
    public void shouldReturnEmptyShoppingCartWhenHasNotProducts() throws Exception {
        //given
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse(Lists.emptyList(), BigDecimal.ZERO);
        when(shoppingCartService.findShoppingCart()).thenReturn(shoppingCartResponse);

        //when
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCart"))
            //then
        .andExpect(jsonPath("$.products").isArray())
        .andExpect(jsonPath("$.totalAmount").value(BigDecimal.ZERO));

    }

    @Test
    public void shouldReturnShoppingCartWhenHasProducts() throws Exception {
        //given
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse(Lists.list(
            new Product(1L,"p1",BigDecimal.valueOf(20),1),
            new Product(2L,"p2",BigDecimal.valueOf(10),2)
        ), BigDecimal.valueOf(40));

        when(shoppingCartService.findShoppingCart()).thenReturn(shoppingCartResponse);

        //when
        mvc.perform(MockMvcRequestBuilders.get("/shoppingCart"))
            //then
        .andExpect(jsonPath("$.products").isArray())
        .andExpect(jsonPath("$.products.[0].id").value(1L))
        .andExpect(jsonPath("$.products.[0].price").value(BigDecimal.valueOf(20)))
        .andExpect(jsonPath("$.products.[1].id").value(2L))
        .andExpect(jsonPath("$.products.[1].price").value(BigDecimal.valueOf(10)))
        .andExpect(jsonPath("$.totalAmount").value(BigDecimal.valueOf(40)));

    }
}
