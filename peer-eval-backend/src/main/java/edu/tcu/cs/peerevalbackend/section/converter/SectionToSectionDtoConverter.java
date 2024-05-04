package edu.tcu.cs.peerevalbackend.section.converter;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDto;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;

import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionToSectionDtoConverter implements Converter<Section, SectionDto> {

    private final StudentToStudentDtoConverter studentToStudentDtoConverter;

    public SectionToSectionDtoConverter(StudentToStudentDtoConverter studentToStudentDtoConverter) {
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
    }

    @Override
    public SectionDto convert(Section source) {

        return new SectionDto(
                source.getId(),
                source.getSectionName(),
                source.getAcademicYear(),
                source.getStudents(),
                source.getInstructor(),
                source.getTeams(),
                source.getRubric()
        );

    }
}
