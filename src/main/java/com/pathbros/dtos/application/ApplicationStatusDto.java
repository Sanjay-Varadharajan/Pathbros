package com.pathbros.dtos.application;

import com.pathbros.enums.ApplicationStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationStatusDto {

    private int applicationId;

    private ApplicationStatus applicationStatus;

    private LocalDateTime appliedOn;
}
