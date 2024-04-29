package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
//import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;


@RestController
@RequestMapping("")
public class StudentController {
    
     private final StudentService studentService;
     private final StudentT studentToStudentDtoConverter;
     private final StudentDtoToStudentConverter studentDtoToStudentConverter;

    public StudentController(StudentService studentService, StudentToStudentDtoConverter studentToStudentDtoConverter) {
        this.studentService = studentService;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable String studentId){
        this.student
    }

}
