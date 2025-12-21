package com.pathbros.dtos.job;

import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCreateDto {
    @NotEmpty(message = "Job Title Must be Filled")
    private String jobTitle;

    @NotEmpty(message = "Job Description Must be Filled")
    private String jobDescription;

    @NotEmpty(message = "Company Name Must be Filled")
    private String jobCompanyName;

    @NotEmpty(message = "Company Mail Must be Filled")
    private String jobCompanyMail;

    @NotEmpty(message = "Hirer Name Must be Filled")
    private String jobPostedBy;

    @NotEmpty(message = "Job Location Must be Filled")
    private String jobLocation;

    @NotEmpty(message = "Job City Must be Filled")
    private String jobCity;

    @NotEmpty(message = "At least one Skill is Required")
    private List<String> jobSkillsRequired;

    @NotNull(message = "Job Experience Must be Included")
    private Experience jobExperience;

    @NotNull(message = "Job Expiry is Required")
    private LocalDate jobExpiryOn;

    @NotEmpty(message = "At least Base Salary must be Mentioned")
    private String jobSalaryRange;

    @NotNull(message = "Job Type must be included")
    private JobType jobType;
}
