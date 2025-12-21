package com.pathbros.service;


import com.pathbros.dtos.application.ApplicationRequestDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.dtos.job.JobResponseDto;
import com.pathbros.dtos.savedjob.SavedJobDto;
import com.pathbros.dtos.user.UserDtoforProfile;
import com.pathbros.enums.ApplicationStatus;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.models.Application;
import com.pathbros.models.Job;
import com.pathbros.models.SavedJob;
import com.pathbros.models.User;
import com.pathbros.repositories.ApplicationRepo;
import com.pathbros.repositories.JobRepo;
import com.pathbros.repositories.SavedJobRepo;
import com.pathbros.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;




    public ResponseEntity<UserDtoforProfile> viewprofile(Principal principal) {


        Optional<User> userVerify=userRepo.findByUserEmail(principal.getName());

        if(userVerify.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDtoforProfile profile=new UserDtoforProfile(userVerify.get());
        return ResponseEntity.ok(profile);
    }


    public ResponseEntity<String> editprofile(Principal principal,UserDtoforProfile userDtoforProfile) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
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



}

