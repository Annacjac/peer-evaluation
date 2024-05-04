package edu.tcu.cs.peerevalbackend.section.converter;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDto;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class SectionDtoToSectionConverter implements Converter<SectionDto, Section> {

    @Override
    public Section convert(SectionDto source) {
        Section section = new Section();
        section.setId(source.id());
        section.setSectionName(source.sectionName());
        section.setAcademicYear(source.academicYear());
        section.setInstructor(source.instructor());
        section.setStudents(source.students());
        section.setTeams(source.teams());
        section.setRubric(source.rubric());
        return section;
    }
}
