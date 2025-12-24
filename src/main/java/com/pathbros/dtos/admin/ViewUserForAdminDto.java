package com.pathbros.dtos.admin;

import com.pathbros.enums.Experience;
import com.pathbros.enums.Role;
import com.pathbros.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewUserForAdminDto {

    private int userId;
    private String userName;
    private String userEmail;
    private String userLocation;
    private String userCity;
    private @NotEmpty(message = "add at least one skill") List<String> userSkillSets;
    private String userCollegeName;
    private Experience userExperience;
    private LocalDateTime userJoinedOn;
    private boolean userIsActive;
    private Role userRole;

    public ViewUserForAdminDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLocation = user.getUserLocation();
        this.userCity = user.getUserCity();
        this.userSkillSets = user.getUserSkillSets();
        this.userCollegeName = user.getUserCollegeName();
        this.userExperience = user.getUserExperience();
        this.userJoinedOn = user.getUserJoinedOn();
        this.userIsActive = user.isUserIsActive();
        this.userRole = user.getUserRole();

    }

}
