package com.syncteam.buscaEmpregoPAOO.dtos;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record CandidateDto(
        UUID id,
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must be at most 100 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @Size(max = 100, message = "Occupation must be at most 100 characters")
        String occupation,

        String gender,

        @CPF(message = "invalid CPF")
        @NotBlank(message = "CPF is required")
        String cpf,

        @Size(max = 15, message = "Phone must be at most 15 characters")
        String phone,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @Size(max = 255, message = "Address must be at most 255 characters")
        String address,

        @Size(max = 50, message = "Education level must be at most 50 characters")
        String educationLevel,

        @NotBlank(message = "Role is required")
        String role 
) {}