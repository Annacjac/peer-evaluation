package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;
import edu.tcu.cs.peerevalbackend.student.dto.StudentDto;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.AlreadyExistsException;
import edu.tcu.cs.peerevalbackend.system.exception.ValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
//import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;


@RestController
@RequestMapping("${api.endpoint.base-url}/students")
public class StudentController {
    
    private final StudentService studentService;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;

    public StudentController(StudentService studentService, StudentDtoToStudentConverter studentDtoToStudentConverter, StudentToStudentDtoConverter studentToStudentDtoConverter){
        this.studentService = studentService;
        this.studentDtoToStudentConverter = studentDtoToStudentConverter;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
    }

    @GetMapping("/{studentId}")
    public Result findStudentByEmail(@PathVariable String studentEmail) {
        Student foundStudent = this.studentService.findByEmail(studentEmail);
        StudentDto studentDto = this.studentToStudentDtoConverter.convert(foundStudent);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", studentDto);
    }

    @PostMapping()
    public Result registerStudent(@Valid @RequestBody StudentDto studentDto){
        Student newStudent = this.studentDtoToStudentConverter.convert(studentDto);
        Student savedStudent = this.studentService.save(newStudent);
        StudentDto savedStudentDto = this.studentToStudentDtoConverter.convert(savedStudent);
        return new Result(true, StatusCode.SUCCESS, "Register Success", savedStudentDto);
    }

    @PutMapping("/{studentEmail}")
    public Result updateStudent(@PathVariable String studentEmail, @Valid @RequestBody StudentDto studentDto) {
        Student update = this.studentDtoToStudentConverter.convert(studentDto);
        Student updatedStudent = this.studentService.update(studentEmail, update);
        StudentDto updatedStudentDto = this.studentToStudentDtoConverter.convert(updatedStudent);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedStudentDto);
    }



}
