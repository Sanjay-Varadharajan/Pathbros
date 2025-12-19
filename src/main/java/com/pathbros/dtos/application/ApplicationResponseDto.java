package com.pathbros.dtos.application;

import com.pathbros.enums.ApplicationStatus;
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
}
