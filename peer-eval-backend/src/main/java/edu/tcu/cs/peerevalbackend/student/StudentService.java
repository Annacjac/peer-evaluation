package edu.tcu.cs.peerevalbackend.student;

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

@Service
 @Transactional
 public class StudentService{

     @Autowired
     private StudentRepository studentRepository;

     @Autowired
     private StudentToStudentDtoConverter toDtoConverter;

     @Autowired
     private StudentDtoToStudentConverter toStudentConverter;

    public Student findByEmail(String email){
        return this.studentRepository.findById(email)
                .orElseThrow(() -> new ObjectNotFoundException("student", email));
    }

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
     public Student findById(String studentId){
         return this.studentRepository.findById(studentId)
                 .orElseThrow(() -> new ObjectNotFoundException("student", studentId));
     }
     public Student findByLastName(String studentLastName){
         return null;
     }
     public List<Student> findBySection()

 }
