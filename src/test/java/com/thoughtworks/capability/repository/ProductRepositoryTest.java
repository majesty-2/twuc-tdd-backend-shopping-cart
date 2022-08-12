package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.domain.Product;
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

        Product cola = products.get(0);
        assertEquals(1L, cola.getId());
        assertEquals("cola", cola.getTitle());
        assertEquals(0, cola.getPrice().compareTo(BigDecimal.ONE));
        assertEquals(1, cola.getQuantity());

        Product candy = products.get(1);
        assertEquals(2L, candy.getId());
        assertEquals("candy", candy.getTitle());
        assertEquals(0, candy.getPrice().compareTo(BigDecimal.TEN));
        assertEquals(2, candy.getQuantity());
    }
}













