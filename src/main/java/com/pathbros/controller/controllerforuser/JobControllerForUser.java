package com.pathbros.controller.controllerforuser;

import com.pathbros.dtos.job.JobResponseDto;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.service.UserServices.JobServiceForUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class JobControllerForUser {



    @Autowired
    JobServiceForUser jobServiceForUser;


    @GetMapping("/jobs/view")
    public ResponseEntity<List<JobResponseDto>> viewalljobs(Principal principal){
        return jobServiceForUser.viewalljobs(principal);
    }



    @GetMapping("/jobs/nearby")
    public ResponseEntity<List<JobResponseDto>> nearbyjobs(Principal principal){
        return jobServiceForUser.nearbyjobs(principal);
    }

    @GetMapping("/jobs/filter")
    public ResponseEntity<List<JobResponseDto>> filterJobs(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Experience experience,
            @RequestParam(required = false) JobType jobType,
            @RequestParam(required = false) String skill
    ) {
        return jobServiceForUser.filterjobs(city,title,experience,jobType,skill);
    }

    @GetMapping("/jobs/sortedby/asc/posteddate")
    public ResponseEntity<List<JobResponseDto>> sortbyposteddateasc(Principal principal){
        return jobServiceForUser.sortbyposteddateasc(principal);
    }

    @GetMapping("/jobs/sortedby/desc/posteddate")
    public ResponseEntity<List<JobResponseDto>> sortbyposteddatedesc(Principal principal){
        return jobServiceForUser.sortbyposteddatedesc(principal);
    }

    @GetMapping("/jobs/company")
    public ResponseEntity<List<JobResponseDto>> getjobbycompany(@RequestParam String companyName,Principal principal){
        return jobServiceForUser.getjobbycompany(companyName,principal);
    }

}
