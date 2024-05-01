package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.dto.SectionDetailDto;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public SectionDetailDto getSectionDetails(Integer sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ObjectNotFoundException("Section", sectionId));

        return convertToDetailDto(section);
    }

    private SectionDetailDto convertToDetailDto(Section section) {
        SectionDetailDto dto = new SectionDetailDto();
        dto.setSectionName(section.getSectionName());
        dto.setAcademicYear(section.getAcademicYear());

        return dto;
    }


    // TODO: add student, teams and instructor to details
}
