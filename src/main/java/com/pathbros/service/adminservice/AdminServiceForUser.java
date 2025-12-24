package com.pathbros.service.adminservice;


import com.pathbros.dtos.admin.UserDeactivateByAdminDto;
import com.pathbros.dtos.admin.ViewUserForAdminDto;
import com.pathbros.models.Admin;
import com.pathbros.models.User;
import com.pathbros.repositories.AdminRepo;
import com.pathbros.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceForUser {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<List<ViewUserForAdminDto>> viewAllUsers(Principal principal) {
        Optional<Admin> loggedInAdmin = adminRepo.findByAdminEmail(principal.getName());

        if(loggedInAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<ViewUserForAdminDto> userList=userRepo.findAll().stream().map(ViewUserForAdminDto::new).toList();

        return ResponseEntity.ok(userList);
    }

    public ResponseEntity<String> deactivateUser(Principal principal, UserDeactivateByAdminDto userDeactivateByAdminDto) {
        Optional<Admin> loggedInAdmin=adminRepo.findByAdminEmail(principal.getName());

        if(loggedInAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Allowed");
        }

        Optional<User> userExists=userRepo.findByUserIdAndUserIsActiveTrue(userDeactivateByAdminDto.getUserId());

        if(userExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        User user=userExists.get();
        user.setUserIsActive(false);


        userRepo.save(user);

        return ResponseEntity.ok("User Deactivated");
    }
}
