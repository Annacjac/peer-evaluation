package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SeniorDesignTeamService{
    public SeniorDesignTeam findByName;
    public SeniorDesignTeam findAllTeams;
    public SeniorDesignTeam save;
    public SeniorDesignTeam update;

    public void delete() {

    }
}
