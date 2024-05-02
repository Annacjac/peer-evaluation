package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;

import java.util.List;

public interface InstructorService {

    Instructor findById(Long id);
    void save(Instructor instructor);
    void assignInstructorsToTeams(SeniorDesignTeamDto seniorDesignTeamDto);
    void removeInstructorFromTeam(Long teamId, Long instructorId);
    List<Instructor> findInstructors(InstructorSearchDto criteria);
    InstructorDto getInstructorById(String id);
    void deactivateInstructor(String id);
    void reactivateInstructor(String id);

}
