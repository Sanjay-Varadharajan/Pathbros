package com.pathbros.service;


import com.pathbros.dtos.job.JobResponseDto;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.models.User;
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
public class JobServiceForUser {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;


    @Autowired
    SavedJobRepo savedJobRepo;

    public ResponseEntity<List<JobResponseDto>> viewalljobs(Principal principal) {
        Optional<User> verifyUser=userRepo.findByUserEmail(principal.getName());

        if(verifyUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<JobResponseDto> allJobs=jobRepo.findAll().stream().map(JobResponseDto::new).toList();

        return ResponseEntity.ok(allJobs);
    }

    public ResponseEntity<List<JobResponseDto>> nearbyjobs(Principal principal) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user=userOptional.get();
        String usercity=user.getUserCity();

        List<JobResponseDto> nearByJobs=jobRepo.findAllByJobCityAndJobIsActiveTrue(usercity).stream().map(JobResponseDto::new).toList();

        return ResponseEntity.ok(nearByJobs);
    }

    public ResponseEntity<List<JobResponseDto>> filterjobs(String city, String title, Experience experience, JobType jobType, String skill) {
        List<JobResponseDto> jobs = jobRepo.filterJobs(city, title, experience, jobType, skill)
                .stream()
                .map(JobResponseDto::new)
                .toList();

        if(jobs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(jobs);
    }


    public ResponseEntity<List<JobResponseDto>> sortbyposteddateasc(Principal principal) {

        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<JobResponseDto> sorted=jobRepo.findAllJobsOldest().stream().map(JobResponseDto::new).toList();

        return ResponseEntity.ok(sorted);
    }


    public ResponseEntity<List<JobResponseDto>> sortbyposteddatedesc(Principal principal) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<JobResponseDto> sorted=jobRepo.findAllJobsNewest().stream().map(JobResponseDto::new).toList();

        return ResponseEntity.ok(sorted);
    }

    public ResponseEntity<List<JobResponseDto>> getjobbycompany(String companyName,Principal principal) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<JobResponseDto> getJobs=jobRepo.findByJobOfCompanyIgnoreCaseAndJobIsActiveTrue(companyName).stream().map(JobResponseDto::new).toList();

        if(getJobs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(getJobs);

    }
}
