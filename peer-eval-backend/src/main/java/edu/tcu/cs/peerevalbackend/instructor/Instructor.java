package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //automatically generate Id starting from default 1
    private Integer id;

    private String name;

//    private String password;

    //when one instructor is saved, all sections for that instructor is saved as well.
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    private List<Section> sections = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "instructor")
    private List<SeniorDesignTeam> teams = new ArrayList<>();

    public Instructor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

//    public void addSection(Section section) {
//        section.setAdmin(this);
//        this.sections.add(section);
//    }

    public Integer getNumberOfSections() {
        return this.sections.size();
    }

    public void removeAllSections() {
        this.sections.stream().forEach(section -> section.setAdmin(null));
        this.sections = null;
    }

    public void removeSection(Section sectionToBeAssigned) {
        //remove section owner
        sectionToBeAssigned.setAdmin(null);
        this.sections.remove(sectionToBeAssigned);
    }

    public List<SeniorDesignTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<SeniorDesignTeam> teams) {
        this.teams = teams;
    }

    public void addTeam(SeniorDesignTeam seniorDesignTeam) {
        seniorDesignTeam.getInstructors().add(this);
        this.teams.add(seniorDesignTeam);
    }
}
