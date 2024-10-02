package com.api_gateway.service.producerService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private static final String PRODUCER_URL = "http://localhost:2222/test";
    
    private final RestTemplate restTemplate;

    public String getProducerTest() {
        return restTemplate.getForObject(PRODUCER_URL, String.class);
    }
}
