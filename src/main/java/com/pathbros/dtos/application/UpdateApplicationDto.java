package com.pathbros.dtos.application;

import com.pathbros.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationDto {

    private int applicationId;

    private ApplicationStatus status;
}
