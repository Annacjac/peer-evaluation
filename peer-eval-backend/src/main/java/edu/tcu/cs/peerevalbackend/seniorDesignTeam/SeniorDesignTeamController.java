package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class SeniorDesignTeamController {
    private final SeniorDesignTeamRepository seniorDesignTeamRepository;
    private final SeniorDesignTeamService seniorDesignTeamService;

@GetMapping
public Result findAllTeams{}

public SeniorDesignTeamController(SeniorDesignTeamRepository seniorDesignTeamRepository, SeniorDesignTeamService seniorDesignTeamService) {
    this.seniorDesignTeamRepository = seniorDesignTeamRepository;
    this.seniorDesignTeamService = seniorDesignTeamService;
}

@GetMapping("/{teamName}")
public Result findByTeamName(@PathVariable String teamName){
    return this.seniorDesignTeamRepository.findByTeamName(teamName)
            .orElseThrow(()->new ObjectNotFoundException("Team name", teamName));
}

@GetMapping
public Result findByStudent(@PathVariable ){}

@GetMapping
public Result findByInstructor{}

@PostMapping
public Result addTeam{}

@DeleteMapping("/{seniorDesignTeamName}")
public Result deleteTeam(@PathVariable String seniorDesignTeamName){
        this.seniorDesignTeamService.delete(seniorDesignTeamName);
    }

@DeleteMapping("/{studentId}")
public Result removeStudent (@PathVariable Integer studentId){

    }

@PostMapping
public Result addStudent{}

@GetMapping
public Result findBySection{}
}


