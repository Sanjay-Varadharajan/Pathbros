package com.pathbros.service;


import com.pathbros.dtos.application.ApplicationRequestDto;
import com.pathbros.dtos.application.ApplicationResponseDto;
import com.pathbros.dtos.job.JobResponseDto;
import com.pathbros.dtos.savedjob.SavedJobDto;
import com.pathbros.dtos.user.UserDtoforProfile;
import com.pathbros.enums.ApplicationStatus;
import com.pathbros.enums.Experience;
import com.pathbros.enums.JobType;
import com.pathbros.models.Application;
import com.pathbros.models.Job;
import com.pathbros.models.SavedJob;
import com.pathbros.models.User;
import com.pathbros.repositories.ApplicationRepo;
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
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    SavedJobRepo savedJobRepo;

    public ResponseEntity<UserDtoforProfile> viewprofile(Principal principal) {


        Optional<User> userVerify=userRepo.findByUserEmail(principal.getName());

        if(userVerify.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UserDtoforProfile profile=new UserDtoforProfile(userVerify.get());
        return ResponseEntity.ok(profile);
    }


    public ResponseEntity<String> editprofile(Principal principal,UserDtoforProfile userDtoforProfile) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        User user=userOptional.get();

        user.setUserName(userDtoforProfile.getUserName());
        user.setUserLocation(userDtoforProfile.getUserLocation());
        user.setUserSkillSets(userDtoforProfile.getUserSkillSets());
        user.setUserCollegeName(userDtoforProfile.getUserCollegeName());
        user.setUserExperience(userDtoforProfile.getUserExperience());

        userRepo.save(user);

        return ResponseEntity.ok().body("User Updated Successfully");
    }

    public ResponseEntity<List<JobResponseDto>> viewalljobs(Principal principal) {
        Optional<User> verifyUser=userRepo.findByUserEmail(principal.getName());

        if(verifyUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<JobResponseDto> allJobs=jobRepo.findAll().stream().map(JobResponseDto::new).toList();

        return ResponseEntity.ok(allJobs);
    }

    public ResponseEntity<String> applyjob(Principal principal, ApplicationRequestDto applicationRequestDto) {
        Optional<User> userVerification=userRepo.findByUserEmail(principal.getName());

        if(userVerification.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }

        Optional<Job> verifyJob=jobRepo.findByJobIdAndJobIsActiveTrue(applicationRequestDto.getJobId());

        if(verifyJob.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job is Not Available");
        }

        if(applicationRepo.existsByApplicantAndAppliedJob(userVerification.get(), verifyJob.get())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already applied to this job");
        }

        Application application=new Application();

        application.setApplicant(userVerification.get());
        application.setAppliedJob(verifyJob.get());
        application.setApplicationOfCompany(verifyJob.get().getJobOfCompany());
        application.setApplicationStatus(ApplicationStatus.APPLIED);

        applicationRepo.save(application);

        return ResponseEntity.status(HttpStatus.CREATED).body("Job Applied Successfully");
    }

    public ResponseEntity<List<ApplicationResponseDto>> viewappliedJob(Principal principal) {
        Optional<User> user=userRepo.findByUserEmail(principal.getName());

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ApplicationResponseDto> applicationResponseDtos=applicationRepo.findByApplicant_UserEmailAndApplicationStatus(principal.getName(), ApplicationStatus.APPLIED).stream().map(ApplicationResponseDto::new).toList();

        return ResponseEntity.ok(applicationResponseDtos);
    }

    public ResponseEntity<String> withdrawAppliedjob(Principal principal, int jobId) {

        Optional<Application> optional=applicationRepo.findByAppliedJob_JobIdAndApplicant_UserEmail(jobId, principal.getName());

        if(optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not Found");
        }

        Application application=optional.get();
        application.setApplicationStatus(ApplicationStatus.WITHDRAWN);

        applicationRepo.save(application);
        return ResponseEntity.status(HttpStatus.OK).body("Application Withdrawn Successfully");
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
        List<JobResponseDto> getJobs=jobRepo.findByJobCompanyIgnoreCaseAndJobIsActiveTrue(companyName).stream().map(JobResponseDto::new).toList();

        if(getJobs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(getJobs);

    }

    public ResponseEntity<String> bookmarkjobs(int jobId, Principal principal) {
        User user = userRepo.findByUserEmail(principal.getName()).orElseThrow();
        Job job = jobRepo.findById(jobId).orElseThrow();

        Optional<SavedJob> existing = savedJobRepo.findByUserAndJob(user, job);
        if(existing.isPresent()) {
            savedJobRepo.delete(existing.get());
            return ResponseEntity.ok("Bookmark removed");
        }

        SavedJob savedJob = new SavedJob();
        savedJob.setUser(user);
        savedJob.setJob(job);
        savedJobRepo.save(savedJob);
        return ResponseEntity.ok("Job bookmarked");
    }

    public ResponseEntity<List<SavedJobDto>> bookmarks(Principal principal) {
        Optional<User> userOptional=userRepo.findByUserEmail(principal.getName());

        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<SavedJobDto> savedJobDtoList=savedJobRepo.findByUser_UserMail(principal.getName()).stream().map(SavedJobDto::new).toList();

        return ResponseEntity.ok(savedJobDtoList);
    }
}

