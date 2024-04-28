package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.system.Result;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class SeniorDesignTeamController {
    private final SeniorDesignTeamRepository seniorDesignTeamRepository;

@GetMapping
public Result findAllTeams{}

public SeniorDesignTeamController(SeniorDesignTeamRepository seniorDesignTeamRepository) {
    this.seniorDesignTeamRepository = seniorDesignTeamRepository;
}

@GetMapping("/{teamName}")
public Result findByTeamName(@PathVariable String teamName){
    return this.seniorDesignTeamRepository.findByTeamName(teamName)
            .orElseThrow(()->new ObjectNotFoundException());
}

@GetMapping
public Result findByStudent(@PathVariable ){}

@GetMapping
public Result findByInstructor{}

@PostMapping
public Result addTeam{}

@DeleteMapping
public Result deleteTeam{}

@DeleteMapping("/{studentId}")
public Result removeStudent (@PathVariable Integer studentId){

    }

@PostMapping
public Result addStudent{}

@GetMapping
public Result findBySection{}
}


