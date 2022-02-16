package com.thoughtworks.capability.infrastructure;

import com.thoughtworks.capability.domain.Example;
import com.thoughtworks.capability.domain.ExampleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class InDataBaseExampleRepository implements ExampleRepository {

    private final JpaExampleRepository jpaExampleRepository;

    @Override
    public Optional<Example> findById(Long id) {
        return jpaExampleRepository.findById(id);
    }

    @Override
    public Example save(Example example) {
        return jpaExampleRepository.save(example);
    }
}
