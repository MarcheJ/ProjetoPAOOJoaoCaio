package com.syncteam.buscaEmpregoPAOO.services;

import com.syncteam.buscaEmpregoPAOO.dtos.CompanyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Company;
import com.syncteam.buscaEmpregoPAOO.mappers.CompanyMapper;
import com.syncteam.buscaEmpregoPAOO.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public List<CompanyDto> findAllCompanies() {
        List<Company> companyResult = repository.findAll();

        return companyResult
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CompanyDto> findCompanyById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public void createCompany(Company company) {
        repository.save(company);
    }

    public boolean updateCompany(UUID id, CompanyDto dto) {
        Optional<Company> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        Company company = optional.get();
        mapper.updateCompanyFromDto(dto, company);

        repository.save(company);
        return true;
    }

    public boolean deleteCompany(UUID id) {
        Optional<Company> companyOptional = repository.findById(id);

        if (companyOptional.isEmpty()) return false;

        repository.delete(companyOptional.get());

        return true;
    }
}