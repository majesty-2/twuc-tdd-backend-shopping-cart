package com.thoughtworks.capability.service;

import com.thoughtworks.capability.common.exception.ExampleNotFoundException;
import com.thoughtworks.capability.domain.Example;
import com.thoughtworks.capability.repository.ExampleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public Example findExampleById(Long id) {
        Optional<Example> example = exampleRepository.findById(id);
        return example.orElseThrow(() -> new ExampleNotFoundException(id));
    }

    public Example createExample(Example example) {
        return exampleRepository.save(example);
    }

}
