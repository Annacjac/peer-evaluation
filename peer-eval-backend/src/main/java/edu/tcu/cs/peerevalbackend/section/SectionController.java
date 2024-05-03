package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
//import edu.tcu.cs.peerevalbackend.service.SectionService;
import edu.tcu.cs.peerevalbackend.section.SectionCreateRequestDto;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping("/sections/{sectionId}")
    public ResponseEntity<?> getSectionDetails(@PathVariable Integer sectionId) {
        try {
            SectionDetailDto sectionDetail = sectionService.getSectionDetails(sectionId);
            return ResponseEntity.ok(sectionDetail);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving section details: " + e.getMessage());
        }
    }
    @PostMapping("/sections")
    public ResponseEntity<?> createSection(@RequestBody SectionCreateRequestDto request) {
        try {
            Section newSection = sectionService.createSection(request.getSectionName(), request.getAcademicYear(), request.getRubricId());
            return ResponseEntity.ok(newSection);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating section: " + e.getMessage());
        }
    }
}
