package com.pathbros.repositories;

import com.pathbros.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {

    Optional<Company> findByCompanyEmail(String Email);
}
