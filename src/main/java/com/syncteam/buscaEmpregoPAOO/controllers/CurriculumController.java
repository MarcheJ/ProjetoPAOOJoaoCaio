package com.syncteam.buscaEmpregoPAOO.controllers;

import com.syncteam.buscaEmpregoPAOO.dtos.CurriculumDto;
import com.syncteam.buscaEmpregoPAOO.services.CurriculumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/curriculum")
@RequiredArgsConstructor
public class CurriculumController {

    private final CurriculumService service;

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody @Valid CurriculumDto dto) {
        List<String> missingKeywords = service.analyzeKeywords(dto);
        service.generatePdf(dto);

        if (!missingKeywords.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Currículo gerado com sugestões de palavras-chave.",
                    "missingKeywords", missingKeywords
            ));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Currículo gerado com sucesso!"
        ));
    }
}
