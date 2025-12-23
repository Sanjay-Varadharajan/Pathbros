package com.pathbros.controller.controllerfororganisation;


import com.pathbros.dtos.application.ApplicationFilterDto;
import com.pathbros.dtos.application.ApplicationViewDtoForCompany;
import com.pathbros.dtos.application.UpdateApplicationDto;
import com.pathbros.models.Application;
import com.pathbros.service.organisationservice.ApplicationServiceForOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/organisation")
public class ApplicationControllerForOrganisation {

    @Autowired
    ApplicationServiceForOrganisation applicationServiceForOrganisation;

    @GetMapping("/application/view")
    public ResponseEntity<List<ApplicationViewDtoForCompany>> viewDtoForCompanies(Principal principal){
        return applicationServiceForOrganisation.viewDtoForCompanies(principal);
    }

    @PutMapping("/application/status/update")
    public ResponseEntity<String> updateApplicationStatus(Principal principal, @RequestBody UpdateApplicationDto updateApplicationDto){
        return applicationServiceForOrganisation.updateStatus(principal,updateApplicationDto);
    }

    @PostMapping("/applications/filter")
    public ResponseEntity<List<Application>> filterApplications(Principal principal, @RequestBody ApplicationFilterDto filterDto ) {
        List<Application> filteredApplications = applicationServiceForOrganisation.filterApplications(principal, filterDto);
        return ResponseEntity.ok(filteredApplications);
    }

}
