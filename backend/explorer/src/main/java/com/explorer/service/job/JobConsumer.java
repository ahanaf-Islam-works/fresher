package com.explorer.service.job;

import com.explorer.model.job.Job;
import com.explorer.model.notification.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobConsumer {
    @Autowired
    private SimpMessagingTemplate template;
    public static final String QUEUE_NAME = "jobs-queue";
    @RabbitListener(queues = QUEUE_NAME)
    public void receiveJob(Job job) {
        Notification newNotification = Notification.builder()
                .companyName(job.getName())
                .jobLink(job.getLink())
                .build();

        template.convertAndSend("/topic/notification/" + 1209015, newNotification);
    }
}
