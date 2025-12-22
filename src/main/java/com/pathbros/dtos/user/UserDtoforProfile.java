package com.pathbros.dtos.user;

import com.pathbros.enums.Experience;
import com.pathbros.enums.Role;
import com.pathbros.models.Company;
import com.pathbros.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoforProfile {
    private int userId;
    private String userName;
    private String userEmail;
    private String userLocation;
    private List<String> userSkillSets;
    private String userCollegeName;
    private Experience userExperience;
    private Role userRole;
    private LocalDateTime userJoinedOn;
    private boolean userIsActive;

    public UserDtoforProfile(@NotNull User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userLocation = user.getUserLocation();
        this.userSkillSets = user.getUserSkillSets();
        this.userCollegeName = user.getUserCollegeName();
        this.userExperience = user.getUserExperience();
        this.userRole = user.getUserRole();
        this.userJoinedOn = user.getUserJoinedOn();
        this.userIsActive = user.isUserIsActive();
    }


}
