package com.syncteam.buscaEmpregoPAOO.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CurriculumDto(
        @NotBlank(message = "Full name is required")
        @Size(max = 100, message = "Full name must be at most 100 characters")
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must be at most 100 characters")
        String email,

        @NotBlank(message = "Phone number is required")
        @Size(max = 20, message = "Phone number must be at most 20 characters")
        String phone,

        @Size(max = 255, message = "Professional objective must be at most 255 characters")
        String professionalObjective,

        @Size(max = 255, message = "Education must be at most 255 characters")
        String education,

        @Size(max = 500, message = "Work experience must be at most 500 characters")
        String experience,

        @Size(max = 500, message = "Skills must be at most 500 characters")
        String skills,

        @NotBlank(message = "Area is required (e.g., backend, frontend, data)")
        String area
) {}