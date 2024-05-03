package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import edu.tcu.cs.peerevalbackend.system.exception.ValidationException;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.tcu.cs.peerevalbackend.student.converter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
 @Transactional
 public class StudentService{

     @Autowired
     private StudentRepository studentRepository;

     @Autowired
     private StudentToStudentDtoConverter toDtoConverter;

     @Autowired
     private StudentDtoToStudentConverter toStudentConverter;

     private SectionRepository sectionRepository;

     public Student save(Student newStudent){
         return this.studentRepository.save(newStudent);
     }

    public Student update(String studentEmail, Student update) {
        return this.studentRepository.findById(studentEmail)
                .map(oldStudent -> {
                    oldStudent.setFirstName(update.getFirstName());
                    oldStudent.setMidInit(update.getMidInit());
                    oldStudent.setLastName(update.getLastName());
                    return this.studentRepository.save(oldStudent);
                })
                .orElseThrow(() -> new ObjectNotFoundException("student", studentEmail));
    }

    public void delete(String studentEmail) {
        this.studentRepository.findById(studentEmail)
                .orElseThrow(() -> new ObjectNotFoundException("student", studentEmail));
        this.studentRepository.deleteById(studentEmail);
    }

     private void validateStudentData(StudentDto studentDto) throws ValidationException {
         List<String> errors = new ArrayList<>();
         if (studentDto.email() == null || !studentDto.email().contains("@")) {
             errors.add("Invalid email address.");
         }
         if (studentDto.firstName() == null || studentDto.firstName().isEmpty()) {
             errors.add("First name must not be empty.");
         }
         if (studentDto.lastName() == null || studentDto.lastName().isEmpty()) {
             errors.add("Last name must not be empty.");
         }
         if (studentDto.password() == null || studentDto.password().length() < 6) {
             errors.add("Password must be at least 6 characters long.");
         }
         if (!errors.isEmpty()) {
             throw new ValidationException(errors);
         }
     }
     public void deleteStudent(String studentId){
         Student studentToBeDeleted = this.studentRepository.findById(studentId)
                 .orElseThrow(() -> new ObjectNotFoundException("student", studentId));
     }
    public Student findById(Integer id){
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("senior design team", id));
    }
     public List<Student> findByFirstName(String studentFirstName){
        return this.studentRepository.findStudentByFirstName(studentFirstName)
                .orElseThrow(() -> new ObjectNotFoundException("student", studentFirstName));
     }
     public List<Student> findByLastName(String studentLastName){
         return this.studentRepository.findStudentsByLastName(studentLastName)
                 .orElseThrow(() -> new ObjectNotFoundException("student", studentLastName));
     }
    public Student findByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        return student.orElseThrow(() -> new RuntimeException("No student found with email: " + email));
    }
     public List<Student> findBySectionName(String sectionName){
         return this.studentRepository.findBySectionName(sectionName)
                 .orElseThrow(() -> new ObjectNotFoundException("section", sectionName));
     }

    public List<Student> findByAcademicYear(String academicYear){
        return this.studentRepository.findByAcademicYear(academicYear)
                .orElseThrow(() -> new ObjectNotFoundException("academic year", academicYear));
    }
    public List<Student> findByTeamName(String teamName){
        return this.studentRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ObjectNotFoundException("team", teamName));
    }

    @Transactional
    public StudentDto registerStudent(StudentDto studentDto) {
        if (studentRepository.findByEmail(studentDto.email()).isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        Student newStudent = toStudentConverter.convert(studentDto);
        Student savedStudent = studentRepository.save(newStudent);
        return toDtoConverter.convert(savedStudent);
    }

 }
