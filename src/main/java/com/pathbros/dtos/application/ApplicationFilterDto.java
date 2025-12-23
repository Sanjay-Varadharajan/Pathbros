package com.pathbros.dtos.application;


import com.pathbros.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFilterDto {


    private ApplicationStatus applicationStatus;

    private List<String> skillSet;

    private String collegeName;
}
