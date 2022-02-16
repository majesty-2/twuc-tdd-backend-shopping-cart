package com.thoughtworks.capability.domain;

import java.util.Optional;

public interface ExampleRepository {

    Optional<Example> findById(Long id);

    Example save(Example example);
}
