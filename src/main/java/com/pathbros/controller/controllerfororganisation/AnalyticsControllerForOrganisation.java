package com.pathbros.controller.controllerfororganisation;


import com.pathbros.service.organisationservice.AnalyticsForOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/organisation")
public class AnalyticsControllerForOrganisation {


    @Autowired
    AnalyticsForOrganisation analyticsForOrganisation;


    @GetMapping("application/total")
    public ResponseEntity<Long> totalApplication(Principal principal){
        return analyticsForOrganisation.totalApplication(principal);
    }
    @GetMapping("job/active/total")
    public ResponseEntity<Long> totalJobsPostedActive(Principal principal){
        return analyticsForOrganisation.totalJobsPostedActive(principal);
    }

    @GetMapping("job/closed/total")
    public ResponseEntity<Long> totalJobsPostedClosed(Principal principal){
        return analyticsForOrganisation.totalJobsPostedClosed(principal);
    }


}
