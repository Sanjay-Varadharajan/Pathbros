package com.pathbros.service.notificationservice;

import com.pathbros.models.Notification;
import com.pathbros.repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class NotificationService {


    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    NotificationRepo notificationRepo;

    public void sendNotificationToUser(Notification notification){
        notificationRepo.save(notification);

        simpMessagingTemplate
                .convertAndSendToUser(
                        notification.getUser().getUserEmail(),
                        "/queue/notifications",
                        notification);
    }

    public void sendNotificationToUsers(List<Notification> notifications){
        notificationRepo.saveAll(notifications);

        for(Notification notify:notifications){
            simpMessagingTemplate
                    .convertAndSendToUser(
                            notify.getUser().getUserEmail(),
                            "/queue/notifications",
                            notify
            );
        }
    }

    public List<Notification> getUserNotification(Principal principal){
        String userEmail= principal.getName();
        return notificationRepo.findByUser_UserEmailOrderByNotificationOnDesc(userEmail);
    }
}


