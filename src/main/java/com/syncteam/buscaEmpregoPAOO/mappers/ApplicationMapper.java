package com.syncteam.buscaEmpregoPAOO.mappers;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.syncteam.buscaEmpregoPAOO.dtos.ApplicationDto;
import com.syncteam.buscaEmpregoPAOO.entities.Application;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    @Mapping(target = "candidate", source = "candidateId")
    Application toEntity(ApplicationDto dto);

    @Mapping(target = "candidateId", source = "candidate.id")
    ApplicationDto toDTO(Application application);

    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "candidate", source = "candidateId")
    void updateApplicationFromDto(ApplicationDto dto, @MappingTarget Application entity);

    default Candidate map(UUID candidateId) {
        if (candidateId == null) return null;
        Candidate candidate = new Candidate();
        candidate.setId(candidateId);
        return candidate;
    }
}

