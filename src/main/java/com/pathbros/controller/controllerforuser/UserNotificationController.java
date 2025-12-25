package com.pathbros.controller.controllerforuser;

import com.pathbros.models.Notification;
import com.pathbros.service.UserServices.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserNotificationController {


    @Autowired
    UserNotificationService userNotificationService;


    @GetMapping("/notifications/view")
    public List<Notification> notificationList(Principal principal){
        return userNotificationService.notificationList(principal);
    }


}
