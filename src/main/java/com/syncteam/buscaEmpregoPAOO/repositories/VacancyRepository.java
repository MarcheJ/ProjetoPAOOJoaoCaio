package com.syncteam.buscaEmpregoPAOO.repositories;

import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancies, UUID> {
    
    List<Vacancies> findByTitle(String title);
    List<Vacancies> findByLocation(String location);

}

