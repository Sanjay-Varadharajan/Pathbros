package com.pathbros.dtos.user;

import com.pathbros.enums.Experience;
import com.pathbros.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private String userEmail;
    private String userLocation;
    private List<String> userSkillSets;
    private String userCollegeName;
    private Experience userExperience;
    private Role userRole;
}
