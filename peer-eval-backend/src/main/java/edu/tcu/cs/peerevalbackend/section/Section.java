package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.admin.Admin;
import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; //Section name is ID

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "section")
    private List<SeniorDesignTeam> team = new ArrayList<>();

    private String academicYear;

    private String sectionName;


    @ManyToOne
    private Admin admin;
    private String firstDate;

    private String lastDate;

    @ManyToOne
    private Rubric rubric;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "section")
    private List<SeniorDesignTeam> teams  =  new ArrayList<>();
    @ElementCollection
    private List<String> activeWeeks =  new ArrayList<>();

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getAcademicYear(){
        return academicYear;
    }
    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    //public void addTeam(SeniorDesignTeam team) {
    //    SeniorDesignTeam.add(team);
    //    team.setSection(this);
    //}

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }


    public void setActiveWeeks(List<String> activeWeeks) {
        this.activeWeeks = activeWeeks;
    }



}


