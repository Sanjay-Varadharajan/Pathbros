package com.pathbros.service.organisationservice;


import com.pathbros.dtos.application.ApplicationFilterDto;
import com.pathbros.dtos.application.ApplicationViewDtoForCompany;
import com.pathbros.dtos.application.UpdateApplicationDto;
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
        Optional<Company> companyExists = companyRepo.findByCompanyEmailAndCompanyIsActiveTrue(principal.getName());

        if (companyExists.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        Company company = companyExists.get();

        List<ApplicationViewDtoForCompany> applications = applicationRepo
                .findByApplicationOfCompany_companyEmail(principal.getName())
                .stream()
                .map(ApplicationViewDtoForCompany::new)
                .toList();

        return ResponseEntity.ok(applications);
    }

    public ResponseEntity<String> updateStatus(Principal principal, UpdateApplicationDto updateApplicationDto) {
        Optional<Application> applicationVerification = applicationRepo.
               findByApplicationIdAndApplicationOfCompany_CompanyEmail(
                        updateApplicationDto.getApplicationId()
                        , principal.getName());

        if (applicationVerification.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application is Not Found");
        }
        if(updateApplicationDto.getStatus() == null){
            return ResponseEntity.badRequest().body("Invalid status");
        }
        Application application = applicationVerification.get();


        application.setApplicationStatus(updateApplicationDto.getStatus());

        applicationRepo.save(application);
        return ResponseEntity.status(HttpStatus.OK).body("Application Status Updated");
    }


    public List<Application> filterApplications(Principal principal, ApplicationFilterDto filterDto) {
        String companyEmail = principal.getName();

        List<Application> applications = applicationRepo.filterApplicationsBasic(
                companyEmail,
                filterDto.getApplicationStatus(),
                filterDto.getCollegeName()
        );

        if (filterDto.getSkillSet() != null && !filterDto.getSkillSet().isEmpty()) {
            applications = applications.stream()
                    .filter(app -> {
                        List<String> candidateSkills = app.getApplicant().getUserSkillSets();
                        return candidateSkills.stream()
                                .anyMatch(filterDto.getSkillSet()::contains);
                    })
                    .toList();
        }
        return applications;
    }
}

