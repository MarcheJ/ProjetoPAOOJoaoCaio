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

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "work_mode", length = 50)
    private String workMode;

    @Column(name = "contract_type", length = 50)
    private String contractType;

    @Column(name = "requirements", columnDefinition = "TEXT")
    private String requirements;

    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private String responsibilities;

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    public Vacancies() {}
}
