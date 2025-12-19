package com.pathbros.dtos.user;

import com.pathbros.enums.Experience;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50)
    private String userName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String userPassword;

    @NotEmpty(message = "Location is required")
    private String userLocation;
;

    @NotEmpty(message = "Add at least one skill")
    private List<String> userSkillSets;

    @NotEmpty(message = "College name is required")
    private String userCollegeName;

    private Experience userExperience;
}
