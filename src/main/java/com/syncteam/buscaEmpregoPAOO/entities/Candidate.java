package com.syncteam.buscaEmpregoPAOO.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "candidates", schema = "public")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Candidate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "occupation", length = 100)
    private String occupation;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "education_level")
    private String educationLevel;

    @CreatedDate
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "role", length = 20, nullable = false)
    private String role = "USER";

    public Candidate() {}
}
