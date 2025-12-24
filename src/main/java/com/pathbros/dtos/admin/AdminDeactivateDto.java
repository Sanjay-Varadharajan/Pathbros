package com.pathbros.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDeactivateDto {

    private int adminId;

    private boolean adminIsActive;
}
