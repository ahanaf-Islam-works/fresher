package com.producer.repository;

import com.producer.model.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends MongoRepository<Producer, String> {
    Optional<Producer> findByRollNumber(Integer rollNumber);
}
