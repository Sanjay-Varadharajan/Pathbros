package com.pathbros.dtos.company;

import com.pathbros.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDto {
    @NotEmpty(message = "Company Name must be provided")
    @Size(min = 2, max = 50)
    private String companyName;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Company Email must be included")
    private String companyEmail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String companyPassword;

    @NotEmpty(message = "Location must be included")
    private String companyLocation;

    @NotEmpty(message = "Phone number is compulsory")
    private String companyPhone;

    @URL(message = "Company Website must be valid")
    private String companyWebsite;

}
