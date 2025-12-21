package com.pathbros.controller.controllerfororganisation;


import com.pathbros.dtos.job.JobCreateDto;
import com.pathbros.service.organisationservice.JobServiceForOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/organisation")
public class JobControllerForOrganisation {


   @Autowired
    JobServiceForOrganisation jobServiceForOrganisation;

    @PostMapping("/job/create")
    public ResponseEntity<String> createJob(Principal principal, @RequestBody JobCreateDto jobCreateDto){
        return jobServiceForOrganisation.createjob(principal,jobCreateDto);
    }
}
