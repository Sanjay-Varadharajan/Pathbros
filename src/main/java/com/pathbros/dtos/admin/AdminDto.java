package com.pathbros.dtos.admin;

import com.pathbros.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private int adminId;
    private String adminName;
    private String adminEmail;
    private Role adminRole;
    private boolean adminIsActive;
    private LocalDateTime adminCreatedOn;
}
