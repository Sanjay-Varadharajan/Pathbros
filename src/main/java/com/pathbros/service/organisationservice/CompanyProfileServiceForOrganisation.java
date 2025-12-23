package com.pathbros.service.organisationservice;


import com.pathbros.dtos.company.CompanyDto;
import com.pathbros.dtos.company.CompanyUpdateDto;
import com.pathbros.models.Company;
import com.pathbros.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class CompanyProfileServiceForOrganisation {

    @Autowired
    CompanyRepo companyRepo;

    public ResponseEntity<CompanyDto> viewProfile(Principal principal) {
        Optional<Company> checkForCompanyExists=companyRepo
                .findByCompanyEmail(principal.getName());

        return checkForCompanyExists
                .map(CompanyDto::new)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> updateProfile(Principal principal, CompanyUpdateDto companyUpdateDto) {

        Optional<Company> checkForCompanyExists=companyRepo
                .findByCompanyEmail(principal.getName());

        if(checkForCompanyExists.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You are forbidden to perform this action");
        }


        Company companyProfile=checkForCompanyExists.get();

        companyProfile.setCompanyName(companyUpdateDto.getCompanyName());
        companyProfile.setCompanyLocation(companyUpdateDto.getCompanyLocation());
        companyProfile.setCompanyCity(companyUpdateDto.getCompanyCity());
        companyProfile.setCompanyPhone(companyUpdateDto.getCompanyPhone());
        companyProfile.setCompanyWebsite(companyUpdateDto.getCompanyWebsite());

        companyRepo.save(companyProfile);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Profile Updated");
    }
}
