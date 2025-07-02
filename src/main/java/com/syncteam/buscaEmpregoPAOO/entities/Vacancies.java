package com.syncteam.buscaEmpregoPAOO.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vacancies", schema = "public")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Vacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "work_mode", length = 50) // exemplo: remoto, híbrido, presencial
    private String workMode;

    @Column(name = "contract_type", length = 50) // exemplo: CLT, PJ, Estágio
    private String contractType;

    @Column(name = "requirements", columnDefinition = "TEXT")
    private String requirements;

    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private String responsibilities;

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    public Vacancies() {}
}
