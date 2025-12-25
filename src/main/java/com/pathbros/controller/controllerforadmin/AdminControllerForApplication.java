package com.pathbros.controller.controllerforadmin;


import com.pathbros.dtos.admin.FetchApplicationDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.service.adminservice.AdminServiceForApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerForApplication {

    @Autowired
    AdminServiceForApplication adminServiceForApplication;

    @GetMapping("/view/applications")
    public ResponseEntity<List<ApplicationResponseDto>> viewAllApplications(Principal principal){
        return adminServiceForApplication.viewAllApplications(principal);
    }

    @GetMapping("/view/application/user")
    public ResponseEntity<List<ApplicationResponseDto>> viewApplicationByUser(Principal principal, @RequestBody FetchApplicationDto fetchApplicationDto){
        return adminServiceForApplication.viewApplicationByUser(principal,fetchApplicationDto);
    }



}
