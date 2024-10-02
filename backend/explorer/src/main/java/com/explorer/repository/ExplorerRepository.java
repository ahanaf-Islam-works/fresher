package com.explorer.repository;

import com.explorer.model.Explorer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExplorerRepository extends MongoRepository<Explorer, String> {
    Optional<Explorer> findByRollNumber(Integer rollNumber);
}

