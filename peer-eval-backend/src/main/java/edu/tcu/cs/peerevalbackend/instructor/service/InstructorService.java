package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SeniorDesignTeamRepository seniordesignteamRepository;
    Instructor findById(Long id);
    void save(Instructor instructor);

    public default void assignInstructorsToTeams(SeniorDesignTeamDto seniorDesignTeamDto) {
        seniorDesignTeamDto.getAssignments().forEach(assignment -> {
            Optional<Instructor> instructor = instructorRepository.findById(assignment.getInstructorId());
            Optional<SeniorDesignTeam> team = seniordesignteamRepository.findById(assignment.getTeamId());
            if (instructor.isPresent() && team.isPresent()) {
                team.get().setInstructor(instructor.get());
                seniordesignteamRepository.save(team.get());
            } else {
                throw new RuntimeException("Instructor or Team not found");
            }
        });
    }

    public default void removeInstructorFromTeam(Long teamId, Long instructorId) {
        var team = seniordesignteamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
        if (team.getInstructor() != null && team.getInstructor().getId().equals(instructorId)) {
            team.setInstructor(null); // Assuming setInstructor(null) will remove the instructor from the team
            seniordesignteamRepository.save(team);
        } else {
            throw new RuntimeException("Instructor not found on this team");
        }
    }

    public default List<Instructor> findInstructors(InstructorSearchDto criteria) {
        List<Instructor> results = new ArrayList<>();
        if (criteria.getFirstName() != null && !criteria.getFirstName().isEmpty()) {
            results.addAll(instructorRepository.findByFirstNameContainingIgnoreCase(criteria.getFirstName()));
        }
        if (criteria.getLastName() != null && !criteria.getLastName().isEmpty()) {
            results.addAll(instructorRepository.findByLastNameContainingIgnoreCase(criteria.getLastName()));
        }
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            results.addAll(instructorRepository.findByEmailContainingIgnoreCase(criteria.getEmail()));
        }
        return results; // This could return duplicates if multiple criteria match the same instructor
    }

    public default InstructorDto getInstructorById(String id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
        return convertToDto(instructor);
    }

    private InstructorDto convertToDto(Instructor instructor) {
        return new InstructorDto(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getEmail()
        );
    }

    public default void deactivateInstructor(String id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (!instructor.isActive()) {
            throw new IllegalStateException("Instructor is already inactive.");
        }

        instructor.setActive(false);
        instructorRepository.save(instructor);
    }

    public default void reactivateInstructor(String id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (instructor.isActive()) {
            throw new IllegalStateException("Instructor is already active.");
        }

        instructor.setActive(true);
        instructorRepository.save(instructor);
    }
    // Other necessary service methods
}
