package edu.tcu.cs.peerevalbackend.section.dto;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;

import java.util.List;

public class SectionDetailDto {
    private String sectionName;  // Ensure this field exists
    private String academicYear; // Ensure this field exists

    private List<SeniorDesignTeam> teams;

    // Getters and Setters
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<SeniorDesignTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<SeniorDesignTeam> teams) {
        this.teams = teams;
    }
}
