package com.pathbros.controller.controllerforadmin;


import com.pathbros.dtos.admin.AdminCreateDto;
import com.pathbros.dtos.admin.AdminDeactivateDto;
import com.pathbros.dtos.admin.AdminDto;
import com.pathbros.service.adminservice.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(Principal principal,
                                           @Valid
                                           @RequestBody AdminCreateDto adminCreateDto){
        return adminService.addAdmin(principal,adminCreateDto);
    }

    @PatchMapping("/deactivate/admin")
    public ResponseEntity<String> deactivateAdmin(Principal principal, @RequestBody AdminDeactivateDto adminDeactivateDto){
        return adminService.deActivateAdmin(principal,adminDeactivateDto);
    }

    @GetMapping("/view/all/admin")
    public ResponseEntity<List<AdminDto>> viewAllAdmin(Principal principal){
        return adminService.viewAllAdmin(principal);
    }
}
