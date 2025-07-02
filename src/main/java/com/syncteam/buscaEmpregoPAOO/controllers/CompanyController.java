package com.syncteam.buscaEmpregoPAOO.controllers;

import com.syncteam.buscaEmpregoPAOO.dtos.CompanyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Company;
import com.syncteam.buscaEmpregoPAOO.mappers.CompanyMapper;
import com.syncteam.buscaEmpregoPAOO.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("companies")
@RequiredArgsConstructor

public class CompanyController implements GenericController {
    private final CompanyService service;
    private final CompanyMapper mapper;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getCompanies() {
        List<CompanyDto> companyDtos = service.findAllCompanies();
        return ResponseEntity.ok(companyDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") String id) {
        UUID companyId = UUID.fromString(id);

        return service.findCompanyById(companyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyDto dto) {
        Company company = mapper.toEntity(dto);
        service.createCompany(company);

        URI location = generateHeaderLocation(company.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCompany(
            @PathVariable("id") String id, @RequestBody @Valid CompanyDto dto) {
        UUID companyId = UUID.fromString(id);

        boolean companyUpdated = service.updateCompany(companyId, dto);
        return companyUpdated
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") String id) {
        UUID companyId = UUID.fromString(id);
        boolean companyDeleted = service.deleteCompany(companyId);

        return companyDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
