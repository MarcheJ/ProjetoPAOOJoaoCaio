package com.syncteam.buscaEmpregoPAOO.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;
import java.util.UUID;

public record CompanyDto(
        UUID id,
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @CNPJ(message = "invalid CNPJ")
        @NotBlank(message = "CNPJ is required")
        @Size(min = 14, max = 14, message = "CNPJ must be exactly 14 digits")
        String cnpj,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email must be at most 100 characters")
        String email,


        @Size(max = 15, message = "Phone must be at most 15 characters")
        String phone,

        @Size(max = 150, message = "Website must be at most 150 characters")
        String website,

        @Size(max = 255, message = "Address must be at most 255 characters")
        String address,

        @PastOrPresent(message = "Registration date cannot be in the future")
        LocalDate registrationDate

) {
}
