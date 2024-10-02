package com.producer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.model.Producer;
import com.producer.services.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final ProducerService producerService;
    @GetMapping
    public String testGet(){
        return "testGet producer";
    }

    @GetMapping("/type")
    public Producer getProducerType(){
        return producerService.getProducerType();
    }

    @GetMapping("/all")
    public List<Producer> getAllProducers(){
        return producerService.getAllProducers();
    }

    @DeleteMapping("/all")
    public boolean deleteAllProducers(){
        return producerService.deleteAllProducers();
    }
}
