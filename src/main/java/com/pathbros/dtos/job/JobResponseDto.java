package com.pathbros.dtos.job;

import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDto {


    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobCompanyName;
    private String jobPostedBy;
    private String jobLocation;
    private double jobLatitude;
    private double jobLongitude;
    private List<String> jobSkillsRequired;
    private Experience jobExperience;
    private LocalDateTime jobPostedOn;
    private LocalDate jobExpiryOn;
    private boolean jobIsActive;
    private String jobSalaryRange;
    private JobType jobType;
}
