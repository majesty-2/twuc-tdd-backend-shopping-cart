package com.thoughtworks.capability.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
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
}
