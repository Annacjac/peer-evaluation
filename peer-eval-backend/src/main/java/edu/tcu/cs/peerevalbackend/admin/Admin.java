package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin implements Serializable {

    @Id
    private Integer id;
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "admin")
    private List<Section> sections;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "admin")
    private List<SeniorDesignTeam> teams = new ArrayList<>();
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public Integer getNumberOfSections(){
        return this.sections.size();
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<SeniorDesignTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<SeniorDesignTeam> teams) {
        this.teams = teams;
    }
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    // A method to help easily add sections to an admin
    public void addSection(Section section) {
        this.sections.add(section);
        section.setAdmin(this);
    }
    public void addTeam(SeniorDesignTeam team) {
        team.setAdmin(this);
        this.teams.add(team);

    }
}