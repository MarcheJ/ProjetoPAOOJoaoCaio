package com.syncteam.buscaEmpregoPAOO.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record VacancyDto(
        UUID id,

        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be at most 100 characters")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Location is required")
        @Size(max = 100, message = "Location must be at most 100 characters")
        String location,

        @PositiveOrZero(message = "Salary must be zero or positive")
        Double salary,

        @Size(max = 50, message = "Work mode must be at most 50 characters")
        String workMode,

        @Size(max = 50, message = "Contract type must be at most 50 characters")
        String contractType,

        String requirements,

        String responsibilities,

        UUID createdBy
) 
    {
}