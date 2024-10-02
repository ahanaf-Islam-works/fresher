package com.explorer.controller.notification;

import com.explorer.model.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {
    @Autowired
    private SimpMessagingTemplate template;
    @PostMapping("/notify/{rollNumber}")
    public Notification getNotification(@PathVariable String rollNumber,
                                        @RequestBody Notification notification) {

        template.convertAndSend("/topic/notification/" + rollNumber, notification);
        return notification;
    }
}
