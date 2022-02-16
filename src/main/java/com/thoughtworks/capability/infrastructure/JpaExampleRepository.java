package com.thoughtworks.capability.infrastructure;

import com.thoughtworks.capability.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExampleRepository extends JpaRepository<Example, Long> {
}
