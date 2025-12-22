package com.pathbros.controller.controllerfororganisation;


import com.pathbros.dtos.application.ApplicationViewDtoForCompany;
import com.pathbros.service.organisationservice.ApplicationServiceForOrganisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
