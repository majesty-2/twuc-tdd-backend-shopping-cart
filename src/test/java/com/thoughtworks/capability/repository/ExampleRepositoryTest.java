package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleRepositoryTest extends WebApplicationTest {
    @Autowired
    private ExampleRepository exampleRepository;

    @Test
    @Sql("/sql/insert_a_example.sql")
    public void shouldReturnExampleWhenExampleIsExist() {
        //given
        Long exampleId = 100L;
        //when
        Optional<Example> actual = exampleRepository.findById(exampleId);

        //then
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), exampleId);
        assertEquals(actual.get().getContent(), "description");
    }

    @Test
    public void shouldCreateExampleSuccess() {
        //given
        Example example = Example.builder().content("content").build();
        //when
        Example actual = exampleRepository.save(example);

        //then
        assertNotNull(actual.getId());
        assertEquals(actual.getContent(), "content");
    }
}
