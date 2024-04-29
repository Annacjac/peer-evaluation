package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import edu.tcu.cs.peerevalbackend.system.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
//import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;


@RestController
@RequestMapping("${api.endpoint.base-url}/students")
public class StudentController {
    
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentDto studentDto) {
        try {
            StudentDto savedStudent = studentService.registerStudent(studentDto);
            return ResponseEntity.ok(savedStudent);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrors());
        }
    }

}
