package com.pathbros.service.adminservice;


import com.pathbros.dtos.company.CompanyCreateDto;
import com.pathbros.models.Admin;
import com.pathbros.models.Company;
import com.pathbros.repositories.AdminRepo;
import com.pathbros.repositories.CompanyRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AdminServiceForCompany {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> addCompany(Principal principal,
                                             CompanyCreateDto companyCreateDto) {

        Optional<Admin> loggedInAdmin=adminRepo
                .findByAdminEmail(principal.getName());

        if(loggedInAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not Allowed");
        }

        Optional<Company> companyExists=companyRepo.findByCompanyEmail(companyCreateDto.getCompanyEmail());

        if(companyExists.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company Already Exists");
        }

        Company company=new Company();
        company.setCompanyName(companyCreateDto.getCompanyName());
        company.setCompanyWebsite(companyCreateDto.getCompanyWebsite());
        company.setCompanyPhone(companyCreateDto.getCompanyPhone());
        company.setCompanyCity(companyCreateDto.getCompanyCity());
        company.setCompanyLocation(companyCreateDto.getCompanyLocation());
        company.setCompanyEmail(companyCreateDto.getCompanyEmail());
        company.setCompanyPassword(passwordEncoder.encode(companyCreateDto.getCompanyPassword()));

        companyRepo.save(company);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Company "+companyCreateDto.getCompanyName()+" Is Added");

    }
}
