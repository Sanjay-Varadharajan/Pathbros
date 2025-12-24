package com.pathbros.controller.controllerforadmin;

import com.pathbros.dtos.company.CompanyCreateDto;
import com.pathbros.service.adminservice.AdminServiceForCompany;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerForCompany {

    @Autowired
    AdminServiceForCompany adminServiceForCompany;

    @PostMapping("/create/company")
    public ResponseEntity<String> addCompany(Principal principal,@Valid @RequestBody CompanyCreateDto companyCreateDto){
        return adminServiceForCompany.addCompany(principal,companyCreateDto);
    }
}
