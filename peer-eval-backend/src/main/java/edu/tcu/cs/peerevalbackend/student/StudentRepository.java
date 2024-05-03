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
    Optional<Student> findById(Integer id);
    Optional<List<Student>> findStudentsByLastName(String lastName);
    Optional<Student> findByEmail(String email);
    Optional<List<Student>> findBySectionName(String sectionName);
    Optional<List<Student>> findByAcademicYear(String academicYear);
    Optional<List<Student>> findByTeamName(String teamName);
    Optional<List<Student>> findStudentByFirstName(String firstName);
    Optional<List<Student>> findAllByEmailIn(List<String> emails);
    
}
