package com.pathbros.dtos.application;

import com.pathbros.enums.ApplicationStatus;
import com.pathbros.models.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationViewDtoForCompany {

    private int applicationId;
    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedOn;


    private int jobId;
    private String jobTitle;

    private int userId;
    private String userName;
    private String userEmail;

    public ApplicationViewDtoForCompany(Application application) {
        this.applicationId = application.getApplicationId();
        this.applicationStatus = application.getApplicationStatus();
        this.appliedOn = application.getAppliedOn();

        this.jobId = application.getAppliedJob().getJobId();
        this.jobTitle = application.getAppliedJob().getJobTitle();

        this.userId = application.getApplicant().getUserId();
        this.userName = application.getApplicant().getUserName();
        this.userEmail = application.getApplicant().getUserEmail();
    }

}
