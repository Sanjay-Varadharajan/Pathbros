package com.pathbros.service.organisationservice;


import com.pathbros.dtos.application.ApplicationViewDtoForCompany;
import com.pathbros.models.Application;
import com.pathbros.models.Company;
import com.pathbros.repositories.ApplicationRepo;
import com.pathbros.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceForOrganisation {


    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    CompanyRepo companyRepo;

    public ResponseEntity<List<ApplicationViewDtoForCompany>> viewDtoForCompanies(Principal principal) {
        Optional<Company> companyExists=companyRepo.findByCompanyEmail(principal.getName());

        if(companyExists.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        Company company=companyExists.get();

        List<ApplicationViewDtoForCompany> applications=applicationRepo
                .findByApplicationOfCompany_companyEmail(principal.getName())
                .stream()
                .map(ApplicationViewDtoForCompany::new)
                .toList();

        return ResponseEntity.ok(applications);
    }
}
