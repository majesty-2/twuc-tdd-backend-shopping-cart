package com.thoughtworks.capability.repository;


import com.thoughtworks.capability.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    void should_return_no_product() {
        List<Product> products = repository.findAll();

        assertEquals(0, products.size());
    }

    @Test
    @Sql("/sql/insert_2_products.sql")
    void should_return_2_products() {
        List<Product> products = repository.findAll();

        assertEquals(2, products.size());

        Product colaProduct = products.get(0);
        assertEquals(1L, colaProduct.getId());
        assertEquals("cola", colaProduct.getTitle());
        assertEquals(0, colaProduct.getPrice().compareTo(BigDecimal.ONE));
        assertEquals(1, colaProduct.getQuantity());

        Product candyProduct = products.get(1);
        assertEquals(2L, candyProduct.getId());
        assertEquals("candy", candyProduct.getTitle());
        assertEquals(0, candyProduct.getPrice().compareTo(BigDecimal.TEN));
        assertEquals(2, candyProduct.getQuantity());
    }
}














