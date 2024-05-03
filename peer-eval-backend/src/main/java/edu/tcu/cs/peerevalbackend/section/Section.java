package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.admin.Admin;
import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.student.Student;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "section")
    private List<SeniorDesignTeam> teams = new ArrayList<>();

    @ManyToOne
    private Instructor instructor;

    private String academicYear;

    private String sectionName;


    @ManyToOne
    private Admin admin;

    @ManyToOne
    private Rubric rubric;
    @OneToMany
    private List<Student> students;

    @ElementCollection
    private List<String> activeWeeks =  new ArrayList<>();

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }


    public void setActiveWeeks(List<String> activeWeeks) {
        this.activeWeeks = activeWeeks;
    }

    public List<SeniorDesignTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<SeniorDesignTeam> teams) {
        this.teams = teams;
    }
    public void getStudents(List<Student> students){this.students = students;}

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}


