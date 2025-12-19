package com.pathbros.dtos.savedjob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedJobDto {

    private int savedJobId;

    private int userId;
    private String userName;

    private int jobId;
    private String jobTitle;

    private LocalDateTime savedOn;

}
