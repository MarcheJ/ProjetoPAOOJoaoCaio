package com.syncteam.buscaEmpregoPAOO.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

import com.syncteam.buscaEmpregoPAOO.dtos.VacancyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "createdBy", source = "createdBy") 
    Vacancies toEntity(VacancyDto dto);

    VacancyDto toDTO(Vacancies vacancy);

    List<Vacancies> toEntityList(List<VacancyDto> dtos);
    List<VacancyDto> toDTOList(List<Vacancies> vacancies);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateVacancyFromDto(VacancyDto dto, @MappingTarget Vacancies entity);
}
