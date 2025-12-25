package com.pathbros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pathbros.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @NotEmpty(message = "Company Name must be Registered")
    @Size(min = 2 ,max = 50)
    private String companyName;

    @Email(message = "Email must be Valid")
    @NotEmpty(message = "Company Mail must be Included")
    @Column(unique = true,nullable = false)
    private String companyEmail;

    @NotEmpty(message = "Password Cannot be Empty")
    @Size(min = 6,message = "Password must be at least 6 character")
    private String companyPassword;

    @NotEmpty(message = "Location Must be Included")
    private String companyLocation;

    @NotEmpty(message = "City must be Included")
    private String companyCity;

    @NotEmpty(message = "Phone number is compulsory")
    private String companyPhone;

    @URL(message = "Company Website must be a valid URL")
    @Column(unique = true,nullable = false)
    private String companyWebsite;

    private Role companyRole=Role.ROLE_RECRUITER;

    private boolean companyApproved = false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime companyJoinedOn;

    private boolean companyIsActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "jobOfCompany", cascade = CascadeType.ALL)
    private List<Job> jobList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "applicationOfCompany", cascade = CascadeType.ALL)
    private List<Application> applicationList = new ArrayList<>();


    @Column(nullable = true)
    private String profileImageUrl;


}
