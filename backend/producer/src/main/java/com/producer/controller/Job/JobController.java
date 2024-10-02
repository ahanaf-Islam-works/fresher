package com.producer.controller.Job;

import com.producer.model.job.Job;
import com.producer.services.rabbit.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/send")
    public Job sendJob() {
        Job newJob = jobService.createNewJob();
        jobService.sendJob(newJob);
        return newJob;
    }
}
