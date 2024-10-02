package com.producer.controller.producer;

import com.producer.model.Producer;
import com.producer.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;
    @PostMapping("/create")
    public boolean createProducer(@RequestBody Producer producer){
        return producerService.createProducer(producer);
    }
}
