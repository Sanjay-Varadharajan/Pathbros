package com.pathbros.models;

import com.pathbros.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"jobId", "userId"}
        )
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "jobId", nullable = false)
    @NotNull(message = "Job reference is required")
    private Job appliedJob;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "Applicant reference is required")
    private User applicant;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus = ApplicationStatus.PENDING;

    private boolean applicationIsActive = true;

    private boolean deletable = true;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime appliedOn;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    @NotNull(message = "Company reference is required")
    private Company applicationOfCompany;
}
