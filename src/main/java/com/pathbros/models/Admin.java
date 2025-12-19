package com.pathbros.models;


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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @NotEmpty(message = "Admin name is necessary")
    private String adminName;

    @Email(message = "Must be a valid Email")
    @Column(unique = true,nullable = false)
    @NotEmpty(message = "Admin mail is necessary")
    private String adminEmail;

    @NotEmpty(message = "password is necessary")
    @Column(nullable = false)
    @Size(min = 6,message = "password Must be at least 6 character")
    private String adminPassword;

    @Enumerated(EnumType.STRING)
    private Role adminRole=Role.ROLE_ADMIN;

    private boolean adminIsActive=true;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime adminCreatedOn;



}
