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
public class ApplicationResponseDto {

    private Integer jobId;
    private String jobTitle;
    private Integer userId;
    private String userName;
    private String companyName;
    private ApplicationStatus applicationStatus;
    private LocalDateTime appliedOn;

    public ApplicationResponseDto(Application application) {
        this.jobId = application.getAppliedJob().getJobId();
        this.jobTitle = application.getAppliedJob().getJobTitle();
        this.userId = application.getApplicant().getUserId();
        this.userName = application.getApplicant().getUserName();
        this.companyName = application.getApplicationOfCompany().getCompanyName();
        this.applicationStatus = application.getApplicationStatus();
        this.appliedOn = application.getAppliedOn();
    }
}
