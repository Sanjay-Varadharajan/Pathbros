package com.pathbros.service.adminservice;


import com.pathbros.dtos.admin.FetchApplicationDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.models.Admin;
import com.pathbros.models.User;
import com.pathbros.repositories.AdminRepo;
import com.pathbros.repositories.ApplicationRepo;
import com.pathbros.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceForApplication {


    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<List<ApplicationResponseDto>> viewAllApplications(Principal principal) {
        Optional<Admin> loggedInAdmin=adminRepo.findByAdminEmail(principal.getName());

        if(loggedInAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<ApplicationResponseDto> applicationList=applicationRepo
                .findAll()
                .stream()
                .map(ApplicationResponseDto::new)
                .toList();

        return ResponseEntity.ok(applicationList);
    }

    public ResponseEntity<List<ApplicationResponseDto>> viewApplicationByUser(
            Principal principal,FetchApplicationDto fetchApplicationDto) {

        Optional<Admin> loggedInAdmin=adminRepo.findByAdminEmail(principal.getName());

        if(loggedInAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
       Optional<User> userExists=userRepo.findByUserEmailAndUserIsActiveTrue(fetchApplicationDto.getUserEmail());

        if(userExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ApplicationResponseDto> applicationListOfUser=applicationRepo.findByApplicant_UserEmail
                (fetchApplicationDto.getUserEmail())
                .stream()
                .map(ApplicationResponseDto::new)
                .toList();

        return ResponseEntity.ok(applicationListOfUser);
    }


}
