package com.pathbros.controller.controllerfororganisation;

import com.pathbros.dtos.company.CompanyDto;
import com.pathbros.dtos.company.CompanyUpdateDto;
import com.pathbros.service.organisationservice.CompanyProfileServiceForOrganisation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/organisation")
public class CompanyProfileController {

    @Autowired
    CompanyProfileServiceForOrganisation companyProfileServiceForOrganisation;

    @GetMapping("/profile/view")
    public ResponseEntity<CompanyDto> viewProfile(Principal principal){
        return companyProfileServiceForOrganisation.viewProfile(principal);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateProfile(Principal principal,
                                                @Valid @RequestBody CompanyUpdateDto companyUpdateDto){
        return companyProfileServiceForOrganisation.updateProfile(principal,companyUpdateDto);
    }



}
