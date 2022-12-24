package com.thoughtworks.capability.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Rollback
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void should_return_empty_product_collection() {
        // given

        // when
        final var products = productRepository.findAll();

        // then
        assertThat(products).isEmpty();

    }

    @Test
    @Sql("/sql/insert_2_products.sql")
    void should_return_2_products() {
        // given

        // when
        final var products = productRepository.findAll();

        // then
        assertThat(products).hasSize(2);

        final var colaProduct = products.get(0);
        assertThat(colaProduct.getId()).isEqualTo(1L);
        assertThat(colaProduct.getName()).isEqualTo("cola");
        assertThat(colaProduct.getPrice()).isEqualByComparingTo(BigDecimal.ONE);
        assertThat(colaProduct.getQuantity()).isEqualByComparingTo(1);

        final var candyProduct = products.get(1);
        assertThat(candyProduct.getId()).isEqualTo(2L);
        assertThat(candyProduct.getName()).isEqualTo("candy");
        assertThat(candyProduct.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(2));
        assertThat(candyProduct.getQuantity()).isEqualTo(2);
    }
}
