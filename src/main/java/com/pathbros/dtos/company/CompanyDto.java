package com.pathbros.dtos.company;
import com.pathbros.enums.Role;
import com.pathbros.models.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private int companyId;
    private String companyName;
    private String companyEmail;
    private String companyLocation;
    private String companyPhone;
    private String companyWebsite;
    private Role companyRole;
    private boolean companyApproved;
    private boolean companyIsActive;

    public CompanyDto(Company company) {
        if (company != null) {
            this.companyId = company.getCompanyId();
            this.companyName = company.getCompanyName();
            this.companyEmail = company.getCompanyEmail();
            this.companyLocation = company.getCompanyLocation();
            this.companyPhone = company.getCompanyPhone();
            this.companyWebsite = company.getCompanyWebsite();
            this.companyRole = company.getCompanyRole();
            this.companyApproved = company.isCompanyApproved();
            this.companyIsActive = company.isCompanyIsActive();
        }

    }
}