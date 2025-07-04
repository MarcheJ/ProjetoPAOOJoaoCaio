package com.syncteam.buscaEmpregoPAOO.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.syncteam.buscaEmpregoPAOO.dtos.CurriculumDto;

@Service
public class CurriculumService {

    public void generatePdf(CurriculumDto dto) {
        Document document = new Document();
        try {
            String staticDir = "curriculums";
            File dir = new File(staticDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String safeName = dto.fullName().replaceAll("[^a-zA-Z0-9_-]", "_");
            String filename = staticDir + "/curriculum_" + safeName + "_" + System.currentTimeMillis() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 11);

            document.add(new Paragraph("Curriculum", titleFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Full Name: " + dto.fullName(), textFont));
            document.add(new Paragraph("Email: " + dto.email(), textFont));
            document.add(new Paragraph("Phone: " + dto.phone(), textFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Professional Objective", sectionFont));
            document.add(new Paragraph(dto.professionalObjective(), textFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Education", sectionFont));
            document.add(new Paragraph(dto.education(), textFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Work Experience", sectionFont));
            document.add(new Paragraph(dto.experience(), textFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Skills", sectionFont));
            document.add(new Paragraph(dto.skills(), textFont));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> analyzeKeywords(CurriculumDto dto) {
        Map<String, List<String>> keywordsByArea = Map.of(
                "backend", List.of("Java", "Spring Boot", "API REST", "MySQL", "Hibernate"),
                "frontend", List.of("HTML", "CSS", "JavaScript", "React", "Figma"),
                "data", List.of("SQL", "Python", "Pandas", "Power BI", "ETL")
        );

        List<String> expectedKeywords = keywordsByArea.getOrDefault(dto.area().toLowerCase(), List.of());

        String content = String.join(" ",
                dto.professionalObjective(),
                dto.education(),
                dto.experience(),
                dto.skills()
        ).toLowerCase();

        return expectedKeywords.stream()
                .filter(keyword -> !content.contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}