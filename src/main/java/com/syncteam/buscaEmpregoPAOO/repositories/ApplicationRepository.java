package com.syncteam.buscaEmpregoPAOO.repositories;

import com.syncteam.buscaEmpregoPAOO.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    List<Application> findByApplicationStatus(String status);
}