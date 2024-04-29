package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
//import edu.tcu.cs.peerevalbackend.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SectionController {

    @Autowired
    private SectionService service;

    // Section Search
    @GetMapping("/sections/search")
    public ResponseEntity<List<Section>> searchSections(@RequestParam(required = false) String sectionName,
                                                        @RequestParam(required = false) String academicYear) {
        List<Section> sections = service.findSections(sectionName, academicYear);
        return ResponseEntity.ok(sections);
    }
}
