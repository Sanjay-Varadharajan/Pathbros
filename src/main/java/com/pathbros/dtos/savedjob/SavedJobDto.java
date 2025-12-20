package com.pathbros.dtos.savedjob;

import com.pathbros.models.SavedJob;
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

    public SavedJobDto(SavedJob savedJob) {
        this.savedJobId = savedJob.getSavedJobId();

        this.userId = savedJob.getUser().getUserId();
        this.userName = savedJob.getUser().getUserName();

        this.jobId = savedJob.getJob().getJobId();
        this.jobTitle = savedJob.getJob().getJobTitle();

        this.savedOn = savedJob.getSavedOn();

    }
}
