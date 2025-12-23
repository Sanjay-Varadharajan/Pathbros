package com.pathbros.service.organisationservice;


import com.pathbros.models.Notification;
import com.pathbros.service.notificationservice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
public class CompanyNotificationService {

    @Autowired
    NotificationService notificationService;

    public List<Notification> notifications(Principal principal) {
        return notificationService.getCompanyNotification(principal);
    }
}
