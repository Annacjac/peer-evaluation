package edu.tcu.cs.peerevalbackend.section.dto;

public class SectionDetailDto {
    private String sectionName;  // Ensure this field exists
    private String academicYear; // Ensure this field exists

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

    // TODO: Show Teams, Team members and Instructors
}
