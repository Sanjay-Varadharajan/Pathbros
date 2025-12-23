package com.pathbros.service.notificationservice;

import com.pathbros.models.Application;
import com.pathbros.models.Company;
import com.pathbros.models.Job;
import com.pathbros.models.Notification;
import com.pathbros.repositories.ApplicationRepo;
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
    ApplicationRepo applicationRepo;

    @Autowired
    NotificationRepo notificationRepo;

    public void sendNotificationToUser(Notification notification) {
        notificationRepo.save(notification);

        simpMessagingTemplate
                .convertAndSendToUser(
                        notification.getUser().getUserEmail(),
                        "/queue/notifications",
                        notification);
    }

    public void sendNotificationToUsers(List<Notification> notifications) {
        notificationRepo.saveAll(notifications);

        for (Notification notify : notifications) {
            simpMessagingTemplate
                    .convertAndSendToUser(
                            notify.getUser().getUserEmail(),
                            "/queue/notifications",
                            notify
                    );
        }
    }

    public void sendNotificationToCompany(Notification notification) {
        notificationRepo.save(notification);

        simpMessagingTemplate.convertAndSendToUser(
                notification.getCompany().getCompanyEmail(),
                "/queue/notifications",
                notification
        );
    }


    public List<Notification> getUserNotification(Principal principal) {
        String userEmail = principal.getName();
        return notificationRepo.findByUser_UserEmailOrderByNotificationOnDesc(userEmail);
    }

    public List<Notification> getCompanyNotification(Principal principal) {
        String companyEmail = principal.getName();
        return notificationRepo.findByCompany_CompanyEmailOrderByNotificationOnDesc(companyEmail);
    }


    public void notifyApplicantsJobClosed(Job job) {

        List<Application> applications =
                applicationRepo.findByAppliedJob_JobId(job.getJobId());

        for (Application app : applications) {

            String email = app.getApplicant().getUserEmail();

            Notification notification = new Notification();
            notification.setUser(app.getApplicant());
            notification.setNotificationMessage(
                    "The job '" + job.getJobTitle() + "' has been closed by the company."
            );
            notification.setRead(false);

            sendNotificationToUser(notification);


        }
    }

    public void notifyCompanyNewApplicant(Application application) {
        String applicantEmail=application.getApplicant().getUserEmail();

        Company company=application.getAppliedJob().getJobOfCompany();

        Notification notification=new Notification();
        notification.setCompany(company);
        notification.setNotificationMessage(
                "New Applicant " + applicantEmail + " Applied For job " + application.getAppliedJob().getJobTitle() + "'"
        );
        notification.setRead(false);
      sendNotificationToCompany(notification);
    }
}



