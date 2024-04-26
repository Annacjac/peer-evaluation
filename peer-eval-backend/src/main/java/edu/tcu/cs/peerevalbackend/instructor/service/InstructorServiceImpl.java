package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Implementation of other methods
}
