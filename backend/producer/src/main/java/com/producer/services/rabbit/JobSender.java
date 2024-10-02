package com.producer.services.rabbit;

import com.producer.config.RabbitMqConfig;
import com.producer.model.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSender {
    private static final String ROUTING_KEY = "job-created";
    public static final String EXCHANGE_NAME = "jobs-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJob(Job job) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, job);
        System.out.println("Job message sent: " + job);
    }
}
