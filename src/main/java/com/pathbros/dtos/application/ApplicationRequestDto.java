    package com.pathbros.dtos.application;

    import com.pathbros.models.Application;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ApplicationRequestDto {

        @NotNull(message = "Job ID is required")
        private Integer jobId;

        @NotNull(message = "User ID is required")
        private Integer userId;


    }
