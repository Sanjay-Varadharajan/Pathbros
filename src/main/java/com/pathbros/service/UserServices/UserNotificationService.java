package com.pathbros.service.UserServices;

import com.pathbros.models.Notification;
import com.pathbros.service.notificationservice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserNotificationService {

    @Autowired
    NotificationService notificationService;


    public List<Notification> notificationList(Principal principal) {
        return notificationService.getUserNotification(principal);
    }
}
