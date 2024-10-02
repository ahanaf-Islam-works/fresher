package com.explorer.service.job;


import com.explorer.model.job.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobListener {

    @KafkaListener(topics = "job-service", groupId = "job-group")
    public void consume(List<Job> jobs) {
        System.out.println("Consumed message: " + jobs);
    }
}