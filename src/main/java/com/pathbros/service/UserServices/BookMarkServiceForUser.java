package com.pathbros.service.UserServices;


import com.pathbros.dtos.savedjob.SavedJobDto;
import com.pathbros.models.Job;
import com.pathbros.models.SavedJob;
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
public class BookMarkServiceForUser {

    @Autowired
    SavedJobRepo savedJobRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    JobRepo jobRepo;

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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<SavedJobDto> savedJobDtoList=savedJobRepo.findByUser_UserEmail(principal.getName())
                .stream()
                .map(SavedJobDto::new)
                .toList();

        return ResponseEntity.ok(savedJobDtoList);
    }
}
