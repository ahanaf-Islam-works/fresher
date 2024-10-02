package com.producer.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.producer.model.Producer;
import com.producer.repository.ProducerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;

    @Transactional
    public boolean createProducer(Producer producer) {
        Producer newProducer = Producer.builder()
                .rollNumber(producer.getRollNumber())
                .company(producer.getCompany())
                .userName(producer.getUserName())
                .build();
        producerRepository.save(newProducer);
        return newProducer.getId() != null;
    }

    @Transactional
    public Producer getProducerType() {
        return Producer.builder().build();
    }

    @Transactional
    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    @Transactional
    public boolean deleteAllProducers() {
        producerRepository.deleteAll();
        return producerRepository.findAll().isEmpty();
    }
}
