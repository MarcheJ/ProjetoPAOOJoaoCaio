package com.syncteam.buscaEmpregoPAOO.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table (name = "companies", schema = "public")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "website", length = 150)
    private String website;

    @Column(name = "address", nullable = false)
    private String address;

    @CreatedDate
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    public Company() {}
}
