package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartRepositoryTest extends WebApplicationTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldReturnEmptyProducts() {
        //given
        //when
        List<Product> products = productRepository.findAll();
        //then
        assertEquals(products.size(), 0);
    }

    @Test
    @Sql("/sql/insert_2_products.sql")
    public void shouldReturnProducts() {
        //given
        //when
        List<Product> products = productRepository.findAll();
        //then
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(BigDecimal.valueOf(20), products.get(0).getPrice());
        assertEquals(2L, products.get(0).getId());
        assertEquals(BigDecimal.valueOf(10), products.get(1).getPrice());
    }
}
