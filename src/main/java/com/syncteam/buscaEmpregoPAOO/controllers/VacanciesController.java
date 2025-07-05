package com.syncteam.buscaEmpregoPAOO.controllers;

import com.syncteam.buscaEmpregoPAOO.dtos.VacancyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;
import com.syncteam.buscaEmpregoPAOO.mappers.VacancyMapper;
import com.syncteam.buscaEmpregoPAOO.services.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacanciesController implements GenericController {

    private final VacancyService service;
    private final VacancyMapper mapper;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<VacancyDto>> getVacancies() {
        List<VacancyDto> vacancyDtos = service.findAllVacancies();
        return ResponseEntity.ok(vacancyDtos);
    }

    // GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable("id") String id) {
        UUID vacancyId = UUID.fromString(id);

        return service.findVacancyById(vacancyId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody @Valid VacancyDto dto) {
        Vacancies vacancy = mapper.toEntity(dto);
        service.createVacancy(vacancy, dto);

        URI location = generateHeaderLocation(vacancy.getId());
        return ResponseEntity.created(location).build();
    }

    // UPDATE 
    @PutMapping("{id}")
    public ResponseEntity<Void> updateVacancy(
            @PathVariable("id") String id, @RequestBody @Valid VacancyDto dto) {
        UUID vacancyId = UUID.fromString(id);

        boolean vacancyUpdated = service.updateVacancy(vacancyId, dto);
        return vacancyUpdated
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // DELETE 
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable("id") String id) {
        UUID vacancyId = UUID.fromString(id);
        boolean vacancyDeleted = service.deleteVacancy(vacancyId);

        return vacancyDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}