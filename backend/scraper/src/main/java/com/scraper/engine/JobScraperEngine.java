package com.scraper.engine;

import com.scraper.model.Job;
import com.scraper.service.scraper.JobScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobScraperEngine {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, List<Job>> kafkaTemplate;
    private final JobScraperService jobScraperService;

    public void sendMessage(String topic, List<Job> jobLists) {
        kafkaTemplate.send(topic, jobLists);
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        List<Job> jobs = jobScraperService.getJobData();
        kafkaTemplate.send(topicName, jobs);
    }
}
