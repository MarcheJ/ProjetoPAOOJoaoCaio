package com.syncteam.buscaEmpregoPAOO.repositories;

import com.syncteam.buscaEmpregoPAOO.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    List<Candidate> findByName(String name);
    List<Candidate> findByEmail(String email);
}
