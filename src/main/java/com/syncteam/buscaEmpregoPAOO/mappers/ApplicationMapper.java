package com.syncteam.buscaEmpregoPAOO.mappers;

import java.util.UUID;
import java.util.List; 

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.syncteam.buscaEmpregoPAOO.dtos.ApplicationDto;
import com.syncteam.buscaEmpregoPAOO.entities.Application;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    @Mapping(target = "candidate", source = "candidateId", qualifiedByName = "mapUuidToCandidate")
    Application toEntity(ApplicationDto dto);

    @Mapping(target = "candidateId", source = "candidate.id")
    ApplicationDto toDTO(Application application);

    List<Application> toEntityList(List<ApplicationDto> dtos);
    List<ApplicationDto> toDTOList(List<Application> applications);

    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "candidate", source = "candidateId", qualifiedByName = "mapUuidToCandidate")
    void updateApplicationFromDto(ApplicationDto dto, @MappingTarget Application entity);

    @Named("mapUuidToCandidate")
    default Candidate map(UUID candidateId) {
        if (candidateId == null) return null;
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);
        return candidate;
    }

    default UUID map(Candidate candidate) {
        return candidate != null ? candidate.getId() : null;
    }
}

