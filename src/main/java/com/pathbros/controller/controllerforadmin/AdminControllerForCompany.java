package com.pathbros.controller.controllerforadmin;

import com.pathbros.dtos.admin.AdminDeactivateDto;
import com.pathbros.dtos.admin.CompanyDeactivateByAdmin;
import com.pathbros.dtos.company.CompanyCreateDto;
import com.pathbros.dtos.company.CompanyDto;
import com.pathbros.service.adminservice.AdminServiceForCompany;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminControllerForCompany {

    @Autowired
    AdminServiceForCompany adminServiceForCompany;

    @PostMapping("/create/company")
    public ResponseEntity<String> addCompany(Principal principal,@Valid @RequestBody CompanyCreateDto companyCreateDto){
        return adminServiceForCompany.addCompany(principal,companyCreateDto);
    }

    @PatchMapping("/deactivate/company")
    public ResponseEntity<String> deactivateCompany(Principal principal, @RequestBody CompanyDeactivateByAdmin companyDeactivateByAdmin){
        return adminServiceForCompany.deactivateCompany(principal,companyDeactivateByAdmin);
    }

    @GetMapping("/view/company/all")
    public ResponseEntity<List<CompanyDto>> viewAllCompany(Principal principal){
        return adminServiceForCompany.viewAllCompany(principal);
    }


}
