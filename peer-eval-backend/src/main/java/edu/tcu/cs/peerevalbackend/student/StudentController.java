package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
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
import edu.tcu.cs.peerevalbackend.student.converter.StudentDtoToStudentConverter;
import edu.tcu.cs.peerevalbackend.student.converter.StudentToStudentDtoConverter;

import java.util.List;
import java.util.stream.Collectors;


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
    public Result findStudentById(@PathVariable int studentId) {
        Student foundStudent = this.studentService.findById(studentId);
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
    @GetMapping("/students")
    public Result findByLastName(@PathVariable String studentLastName){
        List<Student> foundStudents = this.studentService.findByLastName(studentLastName);
        List<StudentDto> studentDtos = foundStudents.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", studentDtos);
    }
    @GetMapping("/students")
    public Result findByAcademicYear(@PathVariable String academicYear){
        List<Student> foundStudents = this.studentService.findByAcademicYear(academicYear);
        List<StudentDto> studentDtos = foundStudents.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", studentDtos);
    }
    @GetMapping("/students")
    public Result findByTeamName(@PathVariable String teamName){
        List<Student> foundStudents = this.studentService.findByTeamName(teamName);
        List<StudentDto> studentDtos = foundStudents.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", studentDtos);
    }

}
