package edu.tcu.cs.peerevalbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
    Optional<List<Student>> findStudentsByLastName(String lastName);
    Optional<Student> findBySectionId(Integer sectionId);
    Optional<List<Student>> findByAcademicYear(String academicYear);
    Optional<List<Student>> findByTeamName(String teamName);
    
}
