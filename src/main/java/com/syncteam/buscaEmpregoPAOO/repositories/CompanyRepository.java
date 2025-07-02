package com.syncteam.buscaEmpregoPAOO.repositories;

import com.syncteam.buscaEmpregoPAOO.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID>  {

    List<Company> findByName(String name);
    List<Company> findByEmail(String email);
}
