package edu.tcu.cs.peerevalbackend.student;

import edu.tcu.cs.peerevalbackend.peerEvaluation.PeerEvaluation;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationDto;
import edu.tcu.cs.peerevalbackend.peerEvaluation.dto.PeerEvaluationReportDto;
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

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("${api.endpoint.base-url}/students")
public class StudentController {
    
    private final StudentService studentService;
    private final StudentDtoToStudentConverter studentDtoToStudentConverter;
    private final StudentToStudentDtoConverter studentToStudentDtoConverter;
    private final InvitationService invitationService;

    public StudentController(StudentService studentService, StudentDtoToStudentConverter studentDtoToStudentConverter, StudentToStudentDtoConverter studentToStudentDtoConverter, InvitationService invitationService){
        this.studentService = studentService;
        this.studentDtoToStudentConverter = studentDtoToStudentConverter;
        this.studentToStudentDtoConverter = studentToStudentDtoConverter;
        this.invitationService = invitationService;
    }

    @GetMapping("/{studentEmail}")
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
    @GetMapping("/students")
    public Result findByLastName(@PathVariable String lastName){
        List<Student> foundStudents = this.studentService.findByLastName(lastName);
        List<StudentDto> studentDtos = foundStudents.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", studentDtos);
    }
    @GetMapping("/students")
    public Result findByFirstName(@PathVariable String firstName){
        List<Student> foundStudents = this.studentService.findByFirstName(firstName);
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
    @GetMapping("/students")
    public Result findBySectionName(@PathVariable String sectionName){
        List<Student> foundStudents = this.studentService.findBySectionName(sectionName);
        List<StudentDto> studentDtos = foundStudents.stream()
                .map(this.studentToStudentDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", studentDtos);
    }
    @PostMapping("/invite-students")
    public Result inviteStudents(@RequestBody String emails){
        List<String> emailList = Arrays.stream(emails.split("[ ;]+"))
                        .map(String::trim)
                        .filter(email -> !email.isEmpty())
                        .collect(Collectors.toList());
        invitationService.inviteStudents(emailList);
        return new Result(true, StatusCode.SUCCESS, "Invitation send success", emailList);
    }

    @PostMapping("/peer-evaluations")
    public ResponseEntity<PeerEvaluationReportDto> submitPeerEvaluation(@RequestBody PeerEvaluationDto peerEvaluationDto) {
        PeerEvaluationReportDto reportDto = studentService.submitPeerEvaluation(peerEvaluationDto);
        return ResponseEntity.ok(reportDto);
    }

    @GetMapping("/peer-evaluations/report")
    public ResponseEntity<PeerEvaluationReportDto> getPeerEvaluationReport(@RequestParam String week, Principal principal) {
        String email = principal.getName(); // Assuming email is used as the username
        PeerEvaluationReportDto reportDto = studentService.getPeerEvaluationReport(email, week);
        return ResponseEntity.ok(reportDto);
    }



}
