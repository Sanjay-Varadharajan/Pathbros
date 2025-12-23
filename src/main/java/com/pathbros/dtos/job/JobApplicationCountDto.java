    package com.pathbros.dtos.job;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class JobApplicationCountDto {
        private int jobId;
        private String jobTitle;
        private long totalApplications;

    }
