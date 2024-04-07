package edu.tcu.cs.peerevalbackend.instructor.service;

import edu.tcu.cs.peerevalbackend.instructor.Instructor;

public interface InstructorService {
    Instructor findById(Long id);
    void save(Instructor instructor);
    // Other necessary service methods
}
