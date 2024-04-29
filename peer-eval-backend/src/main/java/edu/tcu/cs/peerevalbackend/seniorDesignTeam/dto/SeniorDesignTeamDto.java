package edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto;


import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.student.Student;

import java.util.List;

public record SeniorDesignTeamDto(String name,
                                  List<Student> students,
                                  Instructor instructor,
                                  Section section){

}
