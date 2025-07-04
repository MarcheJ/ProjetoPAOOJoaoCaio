package com.syncteam.buscaEmpregoPAOO.services;

import com.syncteam.buscaEmpregoPAOO.dtos.ApplicationDto;
import com.syncteam.buscaEmpregoPAOO.entities.Application;
import com.syncteam.buscaEmpregoPAOO.repositories.ApplicationRepository;
import com.syncteam.buscaEmpregoPAOO.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final CandidateRepository candidateRepository;

    public List<ApplicationDto> findAllApplications() {
        List<Application> applications = repository.findAll();
        return applications.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ApplicationDto> findApplicationById(UUID id) {
        return repository.findById(id).map(this::toDTO);
    }

    public Application createApplication(ApplicationDto dto) {
        var candidate = candidateRepository.findById(dto.candidateId())
            .orElseThrow(() -> new IllegalArgumentException("Candidato não encontrado para o ID informado."));
        Application application = new Application();
        application.setCandidate(candidate);
        application.setJobId(dto.jobId());
        application.setApplicationStatus(dto.applicationStatus());
        return repository.save(application);
    }

    public boolean updateApplication(UUID id, ApplicationDto dto) {
        Optional<Application> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        Application application = optional.get();
        application.setApplicationStatus(dto.applicationStatus());
        repository.save(application);
        return true;
    }

    public boolean deleteApplication(UUID id) {
        Optional<Application> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        repository.delete(optional.get());
        return true;
    }

    private ApplicationDto toDTO(Application application) {
        return new ApplicationDto(
                application.getApplicationId(),
                application.getCandidate().getId(),
                application.getJobId(),
                application.getApplicationDate(),
                application.getApplicationStatus()
        );
    }

    private Application toEntity(ApplicationDto dto) {
        Application application = new Application();
        application.setApplicationId(dto.applicationId());
        application.setApplicationStatus(dto.applicationStatus());
        application.setJobId(dto.jobId());
        candidateRepository.findById(dto.candidateId())
            .ifPresentOrElse(
                application::setCandidate,
                () -> { throw new IllegalArgumentException("Candidato não encontrado para o ID informado."); }
            );
        return application;
    }
}

