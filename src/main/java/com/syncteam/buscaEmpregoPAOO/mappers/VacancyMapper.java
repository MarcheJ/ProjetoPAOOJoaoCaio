package com.syncteam.buscaEmpregoPAOO.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.syncteam.buscaEmpregoPAOO.dtos.VacancyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "creationDate", ignore = true)
    Vacancies toEntity(VacancyDto dto);

    VacancyDto toDTO(Vacancies vacancy);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateVacancyFromDto(VacancyDto dto, @MappingTarget Vacancies entity);
}
