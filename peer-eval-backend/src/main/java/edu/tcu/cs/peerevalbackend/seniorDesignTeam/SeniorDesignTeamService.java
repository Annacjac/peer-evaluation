package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SeniorDesignTeamService{
    public SeniorDesignTeam findByName;
    public SeniorDesignTeam findAllTeams;
    public SeniorDesignTeam save;
    public SeniorDesignTeam update;

    private final SeniorDesignTeamRepository seniorDesignTeamRepository;

    public SeniorDesignTeamService(SeniorDesignTeamRepository seniorDesignTeamRepository) {
        this.seniorDesignTeamRepository = seniorDesignTeamRepository;
    }
    public List<SeniorDesignTeam> findAll(){
        return this.seniorDesignTeamRepository.findAll();
    }

    public void delete(String teamName) {
    SeniorDesignTeam teamToBeDeleted = this.seniorDesignTeamRepository.findById(teamName)
            .orElseThrow(() -> new ObjectNotFoundException("Team", teamName));
    teamToBeDeleted.removeAllStudents();
    this.seniorDesignTeamRepository.deleteById(teamName);
    }
    public SeniorDesignTeam save(SeniorDesignTeam newTeam){
        return this.seniorDesignTeamRepository.save(newTeam);
    }
}
