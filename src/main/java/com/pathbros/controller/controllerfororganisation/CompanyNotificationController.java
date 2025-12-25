package com.pathbros.controller.controllerfororganisation;


import com.pathbros.models.Notification;
import com.pathbros.service.organisationservice.CompanyNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/organisation")
public class CompanyNotificationController {

    @Autowired
    CompanyNotificationService notificationService;


    @GetMapping("/notifications/view")
    public List<Notification> notifications(Principal principal){
        return notificationService.notifications(principal);
    }
}
