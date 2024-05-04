package edu.tcu.cs.peerevalbackend.section.dto;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.Student;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SectionDto(String id,
                         @NotEmpty(message = "name is required") String sectionName,

                         @NotEmpty(message = "year is required") String academicYear,
                         List<Student> students,
                         Instructor instructor,
                         List<SeniorDesignTeam> teams,
                         Rubric rubric) {




}
