package com.pathbros.service.organisationservice;


import com.pathbros.models.Application;
import com.pathbros.repositories.ApplicationRepo;
import com.pathbros.repositories.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsForOrganisation {


    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    JobRepo jobRepo;


    public ResponseEntity<Long> totalApplication(Principal principal) {
        long totalApplication = applicationRepo.countByApplicationOfCompany_CompanyEmail(principal.getName());
        return ResponseEntity.ok(totalApplication);
    }

    public ResponseEntity<Long> totalJobsPostedActive(Principal principal) {
        long totalActiveJobs = jobRepo
                .countByJobOfCompany_CompanyEmailAndJobIsActiveTrue
                        (principal.getName());

        return ResponseEntity.ok(totalActiveJobs);
    }

    public ResponseEntity<Long> totalJobsPostedClosed(Principal principal) {
        long totalInactiveJobs = jobRepo
                .countByJobOfCompany_CompanyEmailAndJobIsActiveFalse
                        (principal.getName());

        return ResponseEntity.ok(totalInactiveJobs);
    }



}
