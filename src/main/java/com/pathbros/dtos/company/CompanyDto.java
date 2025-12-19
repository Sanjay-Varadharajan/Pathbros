package com.pathbros.dtos.company;

import com.pathbros.enums.Role;
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
    private double companyLatitude;
    private double companyLongitude;
    private String companyPhone;
    private String companyWebsite;
    private Role companyRole;
    private boolean companyApproved;
    private boolean companyIsActive;
}
