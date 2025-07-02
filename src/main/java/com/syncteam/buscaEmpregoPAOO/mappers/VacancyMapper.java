package com.syncteam.buscaEmpregoPAOO.mappers;

import com.syncteam.buscaEmpregoPAOO.dtos.VacancyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    Vacancies toEntity(VacancyDto dto);

    VacancyDto toDTO(Vacancies vacancy);

    @Mapping(target = "id", ignore = true)
    void updateVacancyFromDto(VacancyDto dto, @MappingTarget Vacancies entity);
}
