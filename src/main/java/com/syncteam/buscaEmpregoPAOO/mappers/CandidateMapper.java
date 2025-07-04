package com.syncteam.buscaEmpregoPAOO.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.syncteam.buscaEmpregoPAOO.dtos.CandidateDto;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    @Mapping(target = "registrationDate", ignore = true)
    Candidate toEntity(CandidateDto dto);

    CandidateDto toDTO(Candidate candidate);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    void updateCandidateFromDto(CandidateDto dto, @MappingTarget Candidate entity);
}
