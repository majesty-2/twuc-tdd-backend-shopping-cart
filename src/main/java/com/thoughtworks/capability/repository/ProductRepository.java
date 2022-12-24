package com.thoughtworks.capability.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    List findAll();
}
