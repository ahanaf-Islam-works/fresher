package com.scraper.service.scraper;
import com.github.javafaker.Faker;
import com.scraper.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobScraperService {
    public List<Job> getJobData() {
        List<Job> jobs = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 5; i++) {
            String id = "job" + faker.idNumber().valid();
            String title = faker.job().title();
            String company = faker.company().name();
            String location = faker.address().city();
            String postedTime = faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toString();
            String jobUrl = "www." + title.replaceAll("\\s", "").toLowerCase() + ".com";
            List<String> providers = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                providers.add(faker.name().fullName());
            }
            jobs.add(new Job(id, title, company, location, postedTime, jobUrl, providers));
        }
        return jobs;
    }
}