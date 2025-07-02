package com.syncteam.buscaEmpregoPAOO.dtos;

import com.syncteam.buscaEmpregoPAOO.entities.Application;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ApplicationDto(
        UUID applicationId,

        @NotBlank(message = "Candidate is required")
        UUID candidateId,

        @NotBlank(message = "Job vacancy is required")
        UUID jobId,

        LocalDate applicationDate,

        @NotBlank(message = "Status is required")
        @Size(max = 50, message = "Status must be at most 50 characters")
        String applicationStatus
) {

    public static ApplicationDto fromEntity(Application application) {
        return new ApplicationDto(
                application.getApplicationId(),
                application.getCandidate().getId(),
                application.getJobId(),
                application.getApplicationDate(),
                application.getApplicationStatus()
        );
    }

    public Application toEntity() {
        Application application = new Application();
        application.setApplicationId(this.applicationId);
        application.setApplicationStatus(this.applicationStatus);
        application.setJobId(this.jobId);
        return application;
    }
}
