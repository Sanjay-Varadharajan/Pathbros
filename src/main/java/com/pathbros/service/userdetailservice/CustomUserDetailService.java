package com.pathbros.service.userdetailservice;

import com.pathbros.models.Admin;
import com.pathbros.models.Company;
import com.pathbros.models.User;
import com.pathbros.repositories.AdminRepo;
import com.pathbros.repositories.CompanyRepo;
import com.pathbros.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AdminRepo adminRepo;
    private final UserRepo userRepo;
    private final CompanyRepo companyRepo;

    @Override
    public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {
            User user = userRepo.findByUserEmailAndUserIsActiveTrue(eMail).orElse(null);
            if (user != null) {
                return new CustomUserDetails(
                        user,
                        user.getUserEmail(),
                        user.getUserPassword(),
                        user.isUserIsActive(),
                        List.of(new SimpleGrantedAuthority(user.getUserRole().name()))
                );
            }

            Company company = companyRepo.findByCompanyEmailAndCompanyIsActiveTrue(eMail).orElse(null);
            if (company != null) {
                return new CustomUserDetails(
                        company,
                        company.getCompanyEmail(),
                        company.getCompanyPassword(),
                        company.isCompanyIsActive(),
                        List.of(new SimpleGrantedAuthority(company.getCompanyRole().name()))
                );
            }

            Admin admin = adminRepo.findByAdminEmail(eMail).orElse(null);
            if (admin != null) {
                return new CustomUserDetails(
                        admin,
                        admin.getAdminEmail(),
                        admin.getAdminPassword(),
                        true,
                        List.of(new SimpleGrantedAuthority(admin.getAdminRole().name()))
                );
            }

            throw new UsernameNotFoundException("User not found with email: " + eMail);
        }
    }