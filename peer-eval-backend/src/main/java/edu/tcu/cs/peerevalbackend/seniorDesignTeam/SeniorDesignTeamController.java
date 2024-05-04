
package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamDtoToSeniorDesignTeamConverter;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamtoSeniorDesignTeamDtoConverter;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import edu.tcu.cs.peerevalbackend.student.StudentRepository;
import edu.tcu.cs.peerevalbackend.student.StudentService;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class SeniorDesignTeamController {
    private final SeniorDesignTeamRepository seniorDesignTeamRepository;
    private final SeniorDesignTeamService seniorDesignTeamService;
    private final SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter;
    private final SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter;
    private final StudentService studentService;

    private final StudentRepository studentRepository;

    @GetMapping("/{teamName}")
    public Result findAllTeams() {
        List<SeniorDesignTeam> foundTeams = this.seniorDesignTeamService.findAll();

        List<SeniorDesignTeamDto> teamDtos = foundTeams.stream()
                .map(this.seniorDesignTeamtoSeniorDesignTeamDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", teamDtos);
    }

    public SeniorDesignTeamController(SeniorDesignTeamRepository seniorDesignTeamRepository, SeniorDesignTeamService seniorDesignTeamService, SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter, SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter, StudentService studentService, StudentRepository studentRepository) {
        this.seniorDesignTeamRepository = seniorDesignTeamRepository;
        this.seniorDesignTeamService = seniorDesignTeamService;
        this.seniorDesignTeamtoSeniorDesignTeamDtoConverter = seniorDesignTeamtoSeniorDesignTeamDtoConverter;
        this.seniorDesignTeamDtoToSeniorDesignTeamConverter = seniorDesignTeamDtoToSeniorDesignTeamConverter;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Result findByTeamName(@PathVariable String teamName) {
        SeniorDesignTeam seniorDesignTeam = this.seniorDesignTeamService.findById(teamName);
    return new Result(true, StatusCode.SUCCESS, "Find one success", seniorDesignTeam);
    }

    // Use Case 9: The Admin creates a senior design team
    @PostMapping
    public Result addTeam(@Valid @RequestBody SeniorDesignTeamDto seniorDesignTeamDto) {
        SeniorDesignTeam newTeam = this.seniorDesignTeamDtoToSeniorDesignTeamConverter.convert(seniorDesignTeamDto);
        SeniorDesignTeam savedTeam = this.seniorDesignTeamService.save(newTeam);
        SeniorDesignTeamDto savedTeamDto = this.seniorDesignTeamtoSeniorDesignTeamDtoConverter.convert(savedTeam);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedTeamDto);
    }

    @DeleteMapping
    public Result deleteTeam(@PathVariable String teamName) {
        this.seniorDesignTeamService.deleteTeam(teamName);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @DeleteMapping("/{studentEmail}")
    public Result removeStudent(@PathVariable String studentEmail) {
        this.seniorDesignTeamService.removeStudent(studentEmail);
        return new Result(true, StatusCode.SUCCESS, "Removal Success");
    }
    @PutMapping("/students/{teamName}")
    public Result assignStudent(@PathVariable String teamName, @RequestBody String emailList){
        String[] studentEmails = emailList.trim().split(";|\\s+");
        List<String> studentEmailList = Arrays.asList(studentEmails);
        for (String email : studentEmailList){
        this.seniorDesignTeamService.assignStudent(teamName, email);}
        return new Result (true, StatusCode.SUCCESS, "Student Assignment Success");
    }
    // Use Case 10: Edit a Senior Design Team
    @PutMapping("/{teamName}")
    public Result updateTeam(@PathVariable String teamName, @Valid @RequestBody SeniorDesignTeamDto seniorDesignTeamDto) {
        SeniorDesignTeam updatedTeam = seniorDesignTeamDtoToSeniorDesignTeamConverter.convert(seniorDesignTeamDto);
        assert updatedTeam != null;
        SeniorDesignTeam savedTeam = seniorDesignTeamService.update(teamName, updatedTeam);
        return new Result(true, StatusCode.SUCCESS, "Team updated successfully", savedTeam);
    }
}
