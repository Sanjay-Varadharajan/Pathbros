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
public class ApplicationStatusViewDto {

    private int applicationId;

    private ApplicationStatus applicationStatus;

    private LocalDateTime appliedOn;

    public ApplicationStatusViewDto(Application application) {
        this.applicationId=application.getApplicationId();
        this.applicationStatus=application.getApplicationStatus();
        this.appliedOn=application.getAppliedOn();
    }
}
