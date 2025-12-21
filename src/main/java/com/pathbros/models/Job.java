package com.pathbros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

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

    @NotEmpty(message = "City must be Included")
    private String jobCity;


    @ElementCollection
    @NotEmpty(message = "At least one Skill is Required")
    private List<String> jobSkillsRequired = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job Experience Must be Included")
    private Experience jobExperience;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime jobPostedOn;

    @NotNull(message = "Job Expiry is Required")
    private LocalDate jobExpiryOn;

    private boolean jobIsActive = true;

    @NotEmpty(message = "At least Base Salary must be Mentioned")
    private String jobSalaryRange;

    @NotNull(message = "job type must be included")
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @JsonIgnore
    @OneToMany(mappedBy = "appliedJob", cascade = CascadeType.ALL)
    private List<Application> applicationList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    @NotNull(message = "company reference is required")
    private Company jobOfCompany;

    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobList=new ArrayList<>();
}
