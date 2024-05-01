package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final SeniorDesignTeamRepository seniorDesignTeamRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, SeniorDesignTeamRepository seniorDesignTeamRepository) {
        this.instructorRepository = instructorRepository;
        this.seniorDesignTeamRepository = seniorDesignTeamRepository;
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(String.valueOf(id)).orElse(null);
    }

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public void assignInstructorsToTeams(SeniorDesignTeamDto seniorDesignTeamDto) {
        seniorDesignTeamDto.getAssignments().forEach(assignment -> {
            Optional<Instructor> instructor = instructorRepository.findById(assignment.getInstructorId());
            Optional<SeniorDesignTeam> team = seniorDesignTeamRepository.findById(assignment.getTeamId());
            if (instructor.isPresent() && team.isPresent()) {
                team.get().setInstructors((List<Instructor>) instructor.get());
                seniorDesignTeamRepository.save(team.get());
            } else {
                throw new RuntimeException("Instructor or Team not found");
            }
        });
    }

    @Override
    public void removeInstructorFromTeam(Long teamId, Long instructorId) {
        SeniorDesignTeam team = seniorDesignTeamRepository.findById(String.valueOf(teamId))
                .orElseThrow(() -> new RuntimeException("Team not found"));

        boolean foundAndRemoved = team.getInstructors().removeIf(i -> i.getId().equals(instructorId));
        if (foundAndRemoved) {
            seniorDesignTeamRepository.save(team);
        } else {
            throw new RuntimeException("Instructor not found on this team");
        }
    }

    public List<Instructor> findInstructors(InstructorSearchDto criteria) {
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

        return results;
    }

    public InstructorDto getInstructorById(String id) {
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

    public void deactivateInstructor(String id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (!instructor.isActive()) {
            throw new IllegalStateException("Instructor is already inactive.");
        }

        instructor.setActive(false);
        instructorRepository.save(instructor);
    }

    public void reactivateInstructor(String id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (instructor.isActive()) {
            throw new IllegalStateException("Instructor is already active.");
        }

        instructor.setActive(true);
        instructorRepository.save(instructor);
    }
    // Implementation of other methods
}
