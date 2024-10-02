package com.api_gateway.service.producerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.api_gateway.model.producer.Producer;

@Component
public class RestProducer {

    private static final String PRODUCER_URL = "http://localhost:2222/test";

    private final RestTemplate restTemplate;

    // Constructor Injection ensures RestTemplate is properly injected
    @Autowired
    public RestProducer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProducerTest() {
        // Call the producer service using RestTemplate and return the response
        return restTemplate.getForObject(PRODUCER_URL, String.class);
    }

    public String createProducer(Producer producer) {
        return restTemplate.postForObject("http://localhost:2222/producer/create", producer, String.class);
    }
}