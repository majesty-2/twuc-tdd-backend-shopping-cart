package com.thoughtworks.capability.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    Object findAll();
}
