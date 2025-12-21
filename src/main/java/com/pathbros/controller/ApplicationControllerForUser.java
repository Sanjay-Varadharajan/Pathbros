package com.pathbros.controller;

import com.pathbros.dtos.application.ApplicationRequestDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.dtos.application.ApplicationStatusDto;
import com.pathbros.service.ApplicationServiceForUser;
import com.pathbros.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class ApplicationControllerForUser {


    @Autowired
    ApplicationServiceForUser applicationServiceForUser;

    @GetMapping("/appliedjob/view")
    public ResponseEntity<List<ApplicationResponseDto>> viewAppliedJob(Principal principal){
        return applicationServiceForUser.viewappliedJob(principal);
    }

    @DeleteMapping("/appliedjob/{jobId}/withdraw")
    public ResponseEntity<String> withdrawAppliedjob(Principal principal,
                                                     @PathVariable int jobId){
        return applicationServiceForUser.withdrawAppliedjob(principal,jobId);
    }

    @GetMapping("application/viewstatus")
    public ResponseEntity<List<ApplicationStatusDto>> viewApplicationStatus(Principal principal){
        return applicationServiceForUser.viewApplicationStatus();
    }

    @PostMapping("/jobs/apply")
    public ResponseEntity<String> applyforjob(Principal principal,
                                              @Valid
                                              @RequestBody ApplicationRequestDto applicationRequestDto){
        return applicationServiceForUser.applyjob(principal,applicationRequestDto);
    }
}
