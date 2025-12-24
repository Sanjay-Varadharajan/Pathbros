package com.pathbros.service.UserServices;


import com.pathbros.dtos.user.UserDtoforProfile;
import com.pathbros.models.User;
import com.pathbros.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<UserDtoforProfile> viewprofile(Principal principal) {


        Optional<User> userVerify=userRepo.findByUserEmailAndUserIsActiveTrue(principal.getName());

        if(userVerify.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        UserDtoforProfile profile=new UserDtoforProfile(userVerify.get());
        return ResponseEntity.ok(profile);
    }


    public ResponseEntity<String> editprofile(Principal principal,UserDtoforProfile userDtoforProfile) {
        Optional<User> userOptional=userRepo.findByUserEmailAndUserIsActiveTrue(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User Not Found");
        }
        User user=userOptional.get();

        user.setUserName(userDtoforProfile.getUserName());
        user.setUserLocation(userDtoforProfile.getUserLocation());
        user.setUserSkillSets(userDtoforProfile.getUserSkillSets());
        user.setUserCollegeName(userDtoforProfile.getUserCollegeName());
        user.setUserExperience(userDtoforProfile.getUserExperience());

        userRepo.save(user);

        return ResponseEntity.ok().body("User Updated Successfully");
    }


    public ResponseEntity<String> deactivateProfile(Principal principal) {
        Optional<User> userVerify=userRepo.findByUserEmailAndUserIsActiveTrue(principal.getName());

        if(userVerify.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Request is Forbidden");
        }

        User user=userVerify.get();
        user.setUserIsActive(false);
        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Account is Deactivated");
    }
}

