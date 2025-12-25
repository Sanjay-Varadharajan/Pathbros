package com.pathbros.service.organisationservice;


import com.pathbros.dtos.job.JobCreateDto;
import com.pathbros.dtos.job.JobInactiveDto;
import com.pathbros.models.Company;
import com.pathbros.models.Job;
import com.pathbros.models.Notification;
import com.pathbros.repositories.CompanyRepo;
import com.pathbros.repositories.JobRepo;
import com.pathbros.repositories.UserRepo;
import com.pathbros.service.notificationservice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceForOrganisation {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<String> createjob(Principal principal, JobCreateDto jobCreateDto) {
        Optional<Company> companyCheck=companyRepo.findByCompanyEmailAndCompanyIsActiveTrue(principal.getName());

        if(companyCheck.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Organisation Not Found");
        }

        Job job=new Job();

        job.setJobTitle(jobCreateDto.getJobTitle());
        job.setJobDescription(jobCreateDto.getJobDescription());
        job.setJobCompanyName(jobCreateDto.getJobCompanyName());
        job.setJobCompanyMail(principal.getName());
        job.setJobPostedBy(jobCreateDto.getJobPostedBy()); //hr name
        job.setJobLocation(jobCreateDto.getJobLocation());
        job.setJobCity(jobCreateDto.getJobCity());
        job.setJobSkillsRequired(jobCreateDto.getJobSkillsRequired());
        job.setJobExperience(jobCreateDto.getJobExperience());
        job.setJobExpiryOn(jobCreateDto.getJobExpiryOn());
        job.setJobSalaryRange(jobCreateDto.getJobSalaryRange());
        job.setJobType(jobCreateDto.getJobType());

        Job jobSaved=jobRepo.save(job);

        List<com.pathbros.models.User> usersToNotify =
                userRepo.findByUserCityAndUserIsActiveTrue(jobSaved.getJobCity());

        for (com.pathbros.models.User user : usersToNotify) {
            Notification notification = new Notification();
            notification.setUser(user);
            notification.setNotificationMessage(
                    "New job posted: " +jobSaved.getJobTitle() +
                            " at " + jobSaved.getJobCompanyName()
            );

            notificationService.sendNotificationToUser(notification);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Job created successfully");
    }

    public ResponseEntity<String> deActivateJob(Principal principal, JobInactiveDto jobInactiveDto) {
        Optional<Job> checkForExistence=jobRepo.
                findByJobIdAndJobIsActiveAndJobOfCompany_CompanyEmail(
                        jobInactiveDto.getJobId(),true,principal.getName()
                );

        if(checkForExistence.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Not Found");
        }

        Job jobStatus=checkForExistence.get();
        jobStatus.setJobIsActive(false);
        jobRepo.save(jobStatus);


        return ResponseEntity.status(HttpStatus.OK).body("Job Opening closed");
    }
}
