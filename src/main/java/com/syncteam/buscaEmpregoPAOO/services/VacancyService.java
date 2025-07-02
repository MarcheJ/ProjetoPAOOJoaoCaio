package com.syncteam.buscaEmpregoPAOO.services;

import com.syncteam.buscaEmpregoPAOO.dtos.VacancyDto;
import com.syncteam.buscaEmpregoPAOO.entities.Vacancies;
import com.syncteam.buscaEmpregoPAOO.mappers.VacancyMapper;
import com.syncteam.buscaEmpregoPAOO.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository repository;
    private final VacancyMapper mapper;

    public List<VacancyDto> findAllVacancies() {
        List<Vacancies> vacanciesResult = repository.findAll();

        return vacanciesResult
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VacancyDto> findVacancyById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public void createVacancy(Vacancies vacancy) {
        repository.save(vacancy);
    }

    public boolean updateVacancy(UUID id, VacancyDto dto) {
        Optional<Vacancies> optional = repository.findById(id);
        if (optional.isEmpty()) return false;

        Vacancies vacancy = optional.get();
        mapper.updateVacancyFromDto(dto, vacancy);

        repository.save(vacancy);
        return true;
    }

    public boolean deleteVacancy(UUID id) {
        Optional<Vacancies> vacancyOptional = repository.findById(id);

        if (vacancyOptional.isEmpty()) return false;

        repository.delete(vacancyOptional.get());

        return true;
    }
}
