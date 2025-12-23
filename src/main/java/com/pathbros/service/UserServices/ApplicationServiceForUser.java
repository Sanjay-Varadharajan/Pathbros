package com.pathbros.service.UserServices;


import com.pathbros.dtos.application.ApplicationRequestDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.dtos.application.ApplicationStatusViewDto;
import com.pathbros.enums.ApplicationStatus;
import com.pathbros.models.Application;
import com.pathbros.models.Job;
import com.pathbros.models.User;
import com.pathbros.repositories.ApplicationRepo;
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
public class ApplicationServiceForUser {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    NotificationService notificationService;


    public ResponseEntity<String> applyjob(Principal principal, ApplicationRequestDto applicationRequestDto) {
        Optional<User> userVerification = userRepo.findByUserEmail(principal.getName());

        if (userVerification.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        Optional<Job> verifyJob = jobRepo.findByJobIdAndJobIsActiveTrue(applicationRequestDto.getJobId());

        if (verifyJob.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job is Not Available");
        }

        if (applicationRepo.existsByApplicantAndAppliedJob(userVerification.get(), verifyJob.get())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already applied to this job");
        }

        Application application = new Application();

        application.setApplicant(userVerification.get());
        application.setAppliedJob(verifyJob.get());
        application.setApplicationOfCompany(verifyJob.get().getJobOfCompany());
        application.setApplicationStatus(ApplicationStatus.APPLIED);

        applicationRepo.save(application);
        notificationService.notifyCompanyNewApplicant(application);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job Applied Successfully");
    }

    public ResponseEntity<List<ApplicationResponseDto>> viewappliedJob(Principal principal) {
        Optional<User> user = userRepo.findByUserEmail(principal.getName());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ApplicationResponseDto> applicationResponseDtos = applicationRepo.findByApplicant_UserEmailAndApplicationStatus(principal.getName(), ApplicationStatus.APPLIED)
                .stream()
                .map(ApplicationResponseDto::new).
                toList();

        return ResponseEntity.ok(applicationResponseDtos);
    }

    public ResponseEntity<String> withdrawAppliedjob(Principal principal, int jobId) {

        Optional<Application> optional = applicationRepo.findByAppliedJob_JobIdAndApplicant_UserEmail(jobId, principal.getName());

        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not Found");
        }

        Application application = optional.get();
        application.setApplicationStatus(ApplicationStatus.WITHDRAWN);

        applicationRepo.save(application);
        return ResponseEntity.status(HttpStatus.OK).
                body("Application Withdrawn Successfully");
    }

    public ResponseEntity<List<ApplicationStatusViewDto>> viewApplicationStatus(Principal principal) {
        Optional<User> userCheck = userRepo.findByUserEmail(principal.getName());

        if (userCheck.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ApplicationStatusViewDto> applicationStatusDtoList = applicationRepo.findByApplicant_UserEmail(principal.getName())
                .stream()
                .map(ApplicationStatusViewDto::new)
                .toList();

        return ResponseEntity.ok(applicationStatusDtoList);
    }
}
