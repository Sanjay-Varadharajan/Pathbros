package com.pathbros.controller.controllerforuser;


import com.pathbros.dtos.user.UserDtoforProfile;
import com.pathbros.service.UserServices.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping("/userprofile/view")
    public ResponseEntity<UserDtoforProfile> viewprofile(Principal principal){
        return userService.viewprofile(principal);
    }

    @PutMapping("/userprofile/edit")
    public ResponseEntity<String> editprofile(Principal principal,
                                              @Valid
                                              @RequestBody UserDtoforProfile userDtoforProfile){
        return userService.editprofile(principal,userDtoforProfile);
    }

    @DeleteMapping("/userprofile/deactivate")
    public ResponseEntity<String> deactivateProfile(Principal principal){
        return userService.deactivateProfile(principal);
    }
}
