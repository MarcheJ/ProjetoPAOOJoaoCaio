package com.syncteam.buscaEmpregoPAOO.services;

import com.syncteam.buscaEmpregoPAOO.dtos.CandidateDto;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;
import com.syncteam.buscaEmpregoPAOO.mappers.CandidateMapper;
import com.syncteam.buscaEmpregoPAOO.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository repository;
    private final CandidateMapper mapper;

    public List<CandidateDto> findAllCandidates() {
        List<Candidate> candidatesResult = repository.findAll();

        return candidatesResult
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CandidateDto> findCandidateById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public void createCandidate(Candidate candidate) {
        repository.save(candidate);
    }

    public boolean updateCandidate(UUID id, CandidateDto dto) {
        Optional<Candidate> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        Candidate candidate = optional.get();
        mapper.updateCandidateFromDto(dto, candidate);

        repository.save(candidate);
        return true;
    }

    public boolean deleteCandidate(UUID id) {
        Optional<Candidate> candidateOptional = repository.findById(id);

        if (candidateOptional.isEmpty()) return false;

        repository.delete(candidateOptional.get());

        return true;
    }
}
