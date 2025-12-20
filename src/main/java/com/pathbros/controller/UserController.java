package com.pathbros.controller;

import com.pathbros.dtos.application.ApplicationRequestDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.dtos.job.JobResponseDto;
import com.pathbros.dtos.savedjob.SavedJobDto;
import com.pathbros.dtos.user.UserDtoforProfile;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.models.SavedJob;
import com.pathbros.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/jobs/view")
    public ResponseEntity<List<JobResponseDto>> viewalljobs(Principal principal){
        return userService.viewalljobs(principal);
    }

    @PostMapping("/jobs/apply")
    public ResponseEntity<String> applyforjob(Principal principal,
                                              @Valid
                                              @RequestBody ApplicationRequestDto applicationRequestDto){
        return userService.applyjob(principal,applicationRequestDto);
    }

    @GetMapping("/appliedjob/view")
    public ResponseEntity<List<ApplicationResponseDto>> viewAppliedJob(Principal principal){
        return userService.viewappliedJob(principal);
    }

    @DeleteMapping("/appliedjob/{jobId}/withdraw")
    public ResponseEntity<String> withdrawAppliedjob(Principal principal,
                                                     @PathVariable int jobId){
        return userService.withdrawAppliedjob(principal,jobId);
    }

    @GetMapping("/jobs/nearby")
    public ResponseEntity<List<JobResponseDto>> nearbyjobs(Principal principal){
        return userService.nearbyjobs(principal);
    }

    @GetMapping("/jobs/filter")
    public ResponseEntity<List<JobResponseDto>> filterJobs(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Experience experience,
            @RequestParam(required = false) JobType jobType,
            @RequestParam(required = false) String skill
    ) {
        return userService.filterjobs(city,title,experience,jobType,skill);
    }

    @GetMapping("jobs/sortedby/asc/posteddate")
    public ResponseEntity<List<JobResponseDto>> sortbyposteddateasc(Principal principal){
        return userService.sortbyposteddateasc(principal);
    }

    @GetMapping("jobs/sortedby/desc/posteddate")
    public ResponseEntity<List<JobResponseDto>> sortbyposteddatedesc(Principal principal){
        return userService.sortbyposteddatedesc(principal);
    }

    @GetMapping("jobs/company")
    public ResponseEntity<List<JobResponseDto>> getjobbycompany(@RequestParam String companyName,Principal principal){
        return userService.getjobbycompany(companyName,principal);
    }

    @PostMapping("jobs/{jobId}/bookmark")
    public ResponseEntity<String> bookmarkjobs(@PathVariable int jobId,Principal principal){
        return userService.bookmarkjobs(jobId,principal);
    }

    @GetMapping("jobs/me/bookmarks")
    public ResponseEntity<List<SavedJobDto>> bookmarks(Principal principal){
        return userService.bookmarks(principal);
    }



}
