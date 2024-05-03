package edu.tcu.cs.peerevalbackend.instructor;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, String> {
    List<Instructor> findByFirstNameContainingIgnoreCase(String firstName);
    List<Instructor> findByLastNameContainingIgnoreCase(String lastName);
    List<Instructor> findByEmailContainingIgnoreCase(String email);
    Optional<Instructor> findById(Integer id);

    List<Instructor> findAll(Specification<Instructor> spec);}
