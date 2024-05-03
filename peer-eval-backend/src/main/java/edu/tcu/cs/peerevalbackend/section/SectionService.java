package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.rubric.RubricRepository;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDetailDto;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private RubricRepository rubricRepository;

    public SectionDetailDto getSectionDetails(Integer sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ObjectNotFoundException("Section", sectionId));

        return convertToDetailDto(section);
    }

    private SectionDetailDto convertToDetailDto(Section section) {
        SectionDetailDto dto = new SectionDetailDto();
        dto.setSectionName(section.getSectionName());
        dto.setAcademicYear(section.getAcademicYear());
        dto.setTeams(section.getTeams());

        return dto;
    }

    public Section createSection(String sectionName, String academicYear, Integer rubricId) {
        if (sectionRepository.existsBySectionName(sectionName)) {
            throw new IllegalStateException("A section with this name already exists.");
        }

        Rubric rubric = rubricRepository.findById(rubricId)
                .orElseThrow(() -> new ObjectNotFoundException("Rubric", rubricId));

        Section newSection = new Section();
        newSection.setSectionName(sectionName);
        newSection.setAcademicYear(academicYear);
        newSection.setRubric(rubric);

        return sectionRepository.save(newSection);
    }



    // TODO: add student, teams and instructor to details
}
