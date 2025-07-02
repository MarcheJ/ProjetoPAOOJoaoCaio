package com.syncteam.buscaEmpregoPAOO.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syncteam.buscaEmpregoPAOO.dtos.ApplicationDto;
import com.syncteam.buscaEmpregoPAOO.entities.Application;
import com.syncteam.buscaEmpregoPAOO.services.ApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("applications")
@RequiredArgsConstructor
public class ApplicationController implements GenericController {

    private final ApplicationService service;

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getApplications() {
        List<ApplicationDto> applicationDtos = service.findAllApplications();
        return ResponseEntity.ok(applicationDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationDto> getApplicationById(@PathVariable("id") String id) {
        UUID applicationId = UUID.fromString(id);

        return service.findApplicationById(applicationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody @Valid ApplicationDto dto) {
        Application application = service.createApplication(dto);

        URI location = generateHeaderLocation(application.getApplicationId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateApplication(
            @PathVariable("id") String id, @RequestBody @Valid ApplicationDto dto) {
        UUID applicationId = UUID.fromString(id);

        boolean applicationUpdated = service.updateApplication(applicationId, dto);
        return applicationUpdated
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable("id") String id) {
        UUID applicationId = UUID.fromString(id);
        boolean applicationDeleted = service.deleteApplication(applicationId);

        return applicationDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
