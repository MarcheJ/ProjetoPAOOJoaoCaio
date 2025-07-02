package com.syncteam.buscaEmpregoPAOO.mappers;

import com.syncteam.buscaEmpregoPAOO.dtos.CompanyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyDto dto);

    CompanyDto toDTO(Company company);

    @Mapping(target = "id", ignore = true) //
    void updateCompanyFromDto(CompanyDto dto, @MappingTarget Company entity);
}
