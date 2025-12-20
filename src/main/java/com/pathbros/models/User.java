package com.pathbros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pathbros.enums.Experience;
import com.pathbros.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 3 ,max = 50)
    private String userName;

    @NotEmpty(message = "Email is Required")
    @Email(message = "Email should be valid")
    @Column(unique = true,nullable = false)
    private String userEmail;

    @NotEmpty(message = "Password cannot be Empty")
    @Size(min = 6,message = "password must be at least 6 character")
    private String userPassword;

    @NotEmpty(message = "Location is required")
    private String userLocation;

    @NotEmpty(message = "City must be Included")
    private String userCity;

    @ElementCollection
    @NotEmpty(message = "add at least one skill")
    private List<String> userSkillSets = new ArrayList<>();

    @NotEmpty(message = "College name is required")
    private String userCollegeName;

    @Enumerated(EnumType.STRING)
    private Experience userExperience;


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime userJoinedOn;

    private boolean userIsActive = true;

    private Role userRole=Role.ROLE_USER;

    @JsonIgnore
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Application> applicationList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SavedJob> savedJobList=new ArrayList<>();
}
