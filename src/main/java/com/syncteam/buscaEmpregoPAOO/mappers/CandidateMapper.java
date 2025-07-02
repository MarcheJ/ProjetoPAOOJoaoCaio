package com.syncteam.buscaEmpregoPAOO.mappers;

import com.syncteam.buscaEmpregoPAOO.dtos.CandidateDto;
import com.syncteam.buscaEmpregoPAOO.entities.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    Candidate toEntity(CandidateDto dto);

    CandidateDto toDTO(Candidate candidate);

    @Mapping(target = "id", ignore = true)
    void updateCandidateFromDto(CandidateDto dto, @MappingTarget Candidate entity);

}
