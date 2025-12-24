package com.pathbros.repositories;

import com.pathbros.models.Admin;
import com.pathbros.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByAdminEmail(String Email);

    boolean existsByAdminEmail(@Email(message = "Must be a valid email") @NotEmpty(message = "Admin email is required") String adminEmail);

    Optional<Admin> findByAdminIdAndAdminIsActiveTrue(int adminId);



}
