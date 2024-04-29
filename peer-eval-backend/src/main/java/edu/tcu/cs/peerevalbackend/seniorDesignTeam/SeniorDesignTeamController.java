
package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamDtoToSeniorDesignTeamConverter;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.converter.SeniorDesignTeamtoSeniorDesignTeamDtoConverter;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.StatusCode;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class SeniorDesignTeamController {
    private final SeniorDesignTeamRepository seniorDesignTeamRepository;
    private final SeniorDesignTeamService seniorDesignTeamService;
    private final SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter;
    private final SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter;

    @GetMapping("/{teamName}")
    public Result findAllTeams() {
        List<SeniorDesignTeam> foundTeams = this.seniorDesignTeamService.findAll();

        List<SeniorDesignTeamDto> teamDtos = foundTeams.stream()
                .map(this.seniorDesignTeamtoSeniorDesignTeamDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", teamDtos);
    }

    public SeniorDesignTeamController(SeniorDesignTeamRepository seniorDesignTeamRepository, SeniorDesignTeamService seniorDesignTeamService, SeniorDesignTeamtoSeniorDesignTeamDtoConverter seniorDesignTeamtoSeniorDesignTeamDtoConverter, SeniorDesignTeamDtoToSeniorDesignTeamConverter seniorDesignTeamDtoToSeniorDesignTeamConverter) {
        this.seniorDesignTeamRepository = seniorDesignTeamRepository;
        this.seniorDesignTeamService = seniorDesignTeamService;
        this.seniorDesignTeamtoSeniorDesignTeamDtoConverter = seniorDesignTeamtoSeniorDesignTeamDtoConverter;
        this.seniorDesignTeamDtoToSeniorDesignTeamConverter = seniorDesignTeamDtoToSeniorDesignTeamConverter;
    }

    @GetMapping("/{teamName}")
    public Result findByTeamName(@PathVariable String teamName) {
        return this.seniorDesignTeamRepository.findById(teamName)
                .orElseThrow(() -> new ObjectNotFoundException());
    }


    @PostMapping
    public Result addTeam(@Valid @RequestBody SeniorDesignTeamDto seniorDesignTeamDto) {
        SeniorDesignTeam newTeam = this.seniorDesignTeamDtoToSeniorDesignTeamConverter.convert(seniorDesignTeamDto);
        SeniorDesignTeam savedTeam = this.seniorDesignTeamService.save(newTeam);
        SeniorDesignTeamDto savedTeamDto = this.seniorDesignTeamtoSeniorDesignTeamDtoConverter.convert(savedTeam);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedTeamDto);
    }

    @DeleteMapping
    public Result deleteTeam(@PathVariable String teamName) {
        this.seniorDesignTeamService.delete(teamName);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    @DeleteMapping("/{studentId}")
    public Result removeStudent(@PathVariable Integer studentId) {

    }
}
