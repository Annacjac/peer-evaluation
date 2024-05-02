
package edu.tcu.cs.peerevalbackend.seniorDesignTeam;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.Student;
import edu.tcu.cs.peerevalbackend.student.StudentRepository;
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
    private final StudentRepository studentRepository;

    public SeniorDesignTeamService(SeniorDesignTeamRepository seniorDesignTeamRepository, StudentRepository studentRepository) {
        this.seniorDesignTeamRepository = seniorDesignTeamRepository;
        this.studentRepository = studentRepository;
    }
    public List<SeniorDesignTeam> findAll(){
        return this.seniorDesignTeamRepository.findAll();
    }
    public SeniorDesignTeam findById(String teamName){
        return this.seniorDesignTeamRepository.findById(teamName)
                .orElseThrow(() -> new ObjectNotFoundException("senior design team", teamName));
    }
    public void deleteTeam(String teamName) {
    SeniorDesignTeam teamToBeDeleted = this.seniorDesignTeamRepository.findById(teamName)
            .orElseThrow(() -> new ObjectNotFoundException("team", teamName));
    teamToBeDeleted.removeAllStudents();
    this.seniorDesignTeamRepository.deleteById(teamName);
    }
    public SeniorDesignTeam save(SeniorDesignTeam newTeam){
        return this.seniorDesignTeamRepository.save(newTeam);
    }
    public void assignStudent(String teamName, String studentEmail){
        //Find Student by email from DB
        Student studentToBeAssigned = this.studentRepository.findById(studentEmail)
                .orElseThrow(() -> new ObjectNotFoundException("student", studentEmail));
        //Find Senior design team by name
        SeniorDesignTeam seniorDesignTeam = this.seniorDesignTeamRepository.findById(teamName)
                .orElseThrow(() -> new ObjectNotFoundException("team", teamName));
        //Student assignment
        if(studentToBeAssigned.getTeam() != null){
            studentToBeAssigned.getTeam().removeStudent(studentToBeAssigned);
        }
        seniorDesignTeam.addStudent(studentToBeAssigned);
    }
    public void removeStudent(String studentEmail){
        Student studentToBeRemoved = this.studentRepository.findById(studentEmail)
                .orElseThrow(() -> new ObjectNotFoundException("student", studentEmail));
        studentToBeRemoved.setTeam(null);
    }
}
