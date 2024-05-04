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
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    public boolean isActive;
//    private String password;

    //when one instructor is saved, all sections for that instructor is saved as well.
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "instructor")
    private List<Section> sections = new ArrayList<>();
    @ManyToOne
    private SeniorDesignTeam team;

    public Instructor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        section.setAdmin(section.getAdmin());
        this.sections.add(section);
    }

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

    public SeniorDesignTeam getTeam() {
        return team;
    }

    public void setTeam(SeniorDesignTeam team) {
        this.team = team;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
