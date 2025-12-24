package com.pathbros.service.adminservice;

import com.pathbros.dtos.admin.AdminCreateDto;
import com.pathbros.dtos.admin.AdminDeactivateDto;
import com.pathbros.dtos.admin.AdminDto;
import com.pathbros.enums.Role;
import com.pathbros.models.Admin;
import com.pathbros.models.User;
import com.pathbros.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {


    @Autowired
    AdminRepo adminRepo;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> addAdmin(Principal principal, AdminCreateDto adminCreateDto) {

        Optional<Admin> admin=adminRepo.findByAdminEmail(principal.getName());

        if(admin.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Not Allowed");
        }

        if(adminRepo.existsByAdminEmail(adminCreateDto.getAdminEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin with this email already exists");
        }

        Admin newAdmin=new Admin();
        newAdmin.setAdminName(adminCreateDto.getAdminName());
        newAdmin.setAdminEmail(adminCreateDto.getAdminEmail());
        newAdmin.setAdminPassword(passwordEncoder.encode(adminCreateDto.getAdminPassword()));
        newAdmin.setAdminRole(Role.ROLE_ADMIN);

        adminRepo.save(newAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Admin Added");
    }


    public ResponseEntity<String> deActivateAdmin(Principal principal, AdminDeactivateDto adminDeactivateDto) {
        Optional<Admin> loggedIn=adminRepo.findByAdminEmail(principal.getName());

        if(loggedIn.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Allowed");
        }
        Optional<Admin> deActive=adminRepo.findByAdminIdAndAdminIsActiveTrue(adminDeactivateDto.getAdminId());

        if(deActive.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin Not Active");
        }

       Admin admin=deActive.get();

        if(admin.getAdminEmail().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot deactivate yourself");
        }

        admin.setAdminIsActive(false);
        adminRepo.save(admin);

        return ResponseEntity.status(HttpStatus.OK).body("Admin Deactivated");
    }

    public ResponseEntity<List<AdminDto>> viewAllAdmin(Principal principal) {
        Optional<Admin> requestingAdmin=adminRepo.findByAdminEmail(principal.getName());

        if(requestingAdmin.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<AdminDto> adminList=adminRepo.findAll().stream().filter(Admin::isAdminIsActive).map(AdminDto::new).toList();

        return ResponseEntity.ok(adminList);
    }
}
