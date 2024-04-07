package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    // Implementation of other methods
}
