package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SeniorDesignTeamService{
    public SeniorDesignTeam findByName;
    public SeniorDesignTeam findAllTeams;
    public SeniorDesignTeam save;
    public SeniorDesignTeam update;

    public SeniorDesignTeamRepository seniorDesignTeamRepository;

    public void delete(String seniorDesignTeamName) {
        SeniorDesignTeam seniorDesignTeamToBeRemoved = this.seniorDesignTeamRepository.findById(seniorDesignTeamName)
                .orElseThrow(() -> new ObjectNotFoundException("Senior Design Team", seniorDesignTeamName));

        seniorDesignTeamToBeRemoved.
    }
}
