package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
