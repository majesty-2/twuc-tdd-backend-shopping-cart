package com.thoughtworks.capability.service;

import com.thoughtworks.capability.common.exception.ExampleNotFoundException;
import com.thoughtworks.capability.domain.Example;
import com.thoughtworks.capability.domain.ExampleRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExampleServiceTest {
    private final ExampleRepository exampleRepository = mock(ExampleRepository.class);
    private final ExampleService exampleService = new ExampleService(exampleRepository);

    @Test
    public void shouldReturnExampleWhenGivenExampleIdIsValid() {
        Long exampleId = 1L;
        Example example = Example.builder().id(exampleId).build();
        when(exampleRepository.findById(exampleId)).thenReturn(Optional.of(example));

        //when
        Example actual = exampleService.findExampleById(exampleId);

        //then
        assertNotNull(actual);
        assertEquals(actual.getId(), exampleId);
    }

    @Test
    public void shouldThrowExceptionWhenExampleIsNotExist() {
        //given
        Long exampleId = 1L;
        when(exampleRepository.findById(exampleId)).thenReturn(Optional.empty());

        assertThrows(ExampleNotFoundException.class, () -> {
            exampleService.findExampleById(exampleId);
        });
    }

    @Test
    public void shouldCreateExampleSuccess() {
        //given
        Example example = Example.builder().content("content").build();
        when(exampleRepository.save(example)).thenReturn(Example.builder().id(1L).content("content").build());

        //when
        Example actual = exampleService.createExample(example);

        //then
        assertNotNull(actual);
        assertEquals(actual.getId(), 1L);
        verify(exampleRepository, times(1)).save(example);
    }
}
