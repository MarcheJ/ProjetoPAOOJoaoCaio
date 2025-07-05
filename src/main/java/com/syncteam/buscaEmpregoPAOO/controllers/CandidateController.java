package com.syncteam.buscaEmpregoPAOO.controllers;

import com.syncteam.buscaEmpregoPAOO.dtos.CandidateDto;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;
import com.syncteam.buscaEmpregoPAOO.mappers.CandidateMapper;
import com.syncteam.buscaEmpregoPAOO.services.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("candidates")
@RequiredArgsConstructor
public class CandidateController implements GenericController {
    private final CandidateService service;
    private final CandidateMapper mapper;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CandidateDto>> getCandidates() {
        List<CandidateDto> candidateDtos = service.findAllCandidates();
        return ResponseEntity.ok(candidateDtos);
    }

    // GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable("id") String id) {
        // Converte para UUID
        UUID candidateId = UUID.fromString(id);

        return service.findCandidateById(candidateId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CandidateDto> createCandidate(@RequestBody @Valid CandidateDto dto) {
        Candidate candidate = mapper.toEntity(dto);
        service.createCandidate(candidate);

        // Necessário para retorno com status CREATED
        URI location = generateHeaderLocation(candidate.getId());
        return ResponseEntity.created(location).build();
    }

    // UPDATE (by id)
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCandidate(
            @PathVariable("id") String id, @RequestBody @Valid CandidateDto dto) {
        UUID candidateId = UUID.fromString(id);

        boolean candidateUpdated = service.updateCandidate(candidateId, dto);
        return candidateUpdated
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build(); 
    }

    // DELETE (by id)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable("id") String id) {
        UUID candidateId = UUID.fromString(id);
        boolean candidateDeleted = service.deleteCandidate(candidateId);

        return candidateDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
