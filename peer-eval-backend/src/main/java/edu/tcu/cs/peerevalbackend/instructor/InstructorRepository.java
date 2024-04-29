package edu.tcu.cs.peerevalbackend.instructor;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, String> {
    // Method to find an Instructor by email, if needed
    List<Instructor> findByFirstNameContainingIgnoreCase(String firstName);
    List<Instructor> findByLastNameContainingIgnoreCase(String lastName);
    List<Instructor> findByEmailContainingIgnoreCase(String email);
    List<Instructor> findByIdcontainingIgnoreCase(String id);
}
