package com.pathbros.dtos.company;

import com.pathbros.models.Company;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {
    @NotEmpty(message = "Company Name must be Registered")
    @Size(min = 2 ,max = 50)
    private String companyName;


    @NotEmpty(message = "Location Must be Included")
    private String companyLocation;

    @NotEmpty(message = "City must be Included")
    private String companyCity;

    @NotEmpty(message = "Phone number is compulsory")
    private String companyPhone;

    @URL(message = "Company Website must be a valid URL")
    private String companyWebsite;


    public CompanyUpdateDto(Company company) {
        this.companyName = company.getCompanyName();
        this.companyLocation = company.getCompanyLocation();
        this.companyCity = company.getCompanyCity();
        this.companyPhone = company.getCompanyPhone();
        this.companyWebsite = company.getCompanyWebsite();
    }

}
