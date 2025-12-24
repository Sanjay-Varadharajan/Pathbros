package com.pathbros.dtos.admin;

import com.pathbros.enums.Role;
import com.pathbros.models.Admin;
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

    public AdminDto(Admin admin) {
        this.adminId = admin.getAdminId();
        this.adminName = admin.getAdminName();
        this.adminEmail = admin.getAdminEmail();
        this.adminRole = admin.getAdminRole();
        this.adminIsActive = admin.isAdminIsActive();
        this.adminCreatedOn = admin.getAdminCreatedOn();
    }
}
