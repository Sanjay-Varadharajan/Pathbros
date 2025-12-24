package com.pathbros.controller.controllerforadmin;


import com.pathbros.dtos.admin.UserDeactivateByAdminDto;
import com.pathbros.dtos.admin.ViewUserForAdminDto;
import com.pathbros.service.adminservice.AdminServiceForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerForUsers {

    @Autowired
    AdminServiceForUser adminServiceForUser;


    @GetMapping("/view/all/users")
    public ResponseEntity<List<ViewUserForAdminDto>> viewAllUsers(Principal principal){
        return adminServiceForUser.viewAllUsers(principal);
    }

    @PatchMapping("/deactive/users")
    public ResponseEntity<String> deactivateUser(Principal principal, @RequestBody UserDeactivateByAdminDto userDeactivateByAdminDto){
        return adminServiceForUser.deactivateUser(principal, userDeactivateByAdminDto);
    }



}
