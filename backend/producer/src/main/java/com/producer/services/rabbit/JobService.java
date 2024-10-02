package com.producer.services.rabbit;

import com.github.javafaker.Faker;
import com.producer.model.job.Job;
import com.producer.utils.GenerateItems;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class JobService {
    @Autowired
    private JobSender jobSender;

    public Job createNewJob() {
        Faker faker = new Faker();

        String id = "job" + GenerateItems.generateRandomString(5);
        String title = faker.job().title();
        String company = faker.company().name();
        String location = faker.address().city();
        String jobUrl = "www." + title.replaceAll("\\s", "").toLowerCase() + ".com";

        return Job.builder()
                .id(id)
                .name(title)
                .companyName(company)
                .description(location)
                .link(jobUrl)
                .build();
    }

    public void sendJob(Job job) {
        jobSender.sendJob(job);
    }
}
