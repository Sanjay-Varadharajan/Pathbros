package com.pathbros.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateDto {
    @NotEmpty(message = "Admin name is required")
    private String adminName;

    @Email(message = "Must be a valid email")
    @NotEmpty(message = "Admin email is required")
    private String adminEmail;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String adminPassword;
}
