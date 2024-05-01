package edu.tcu.cs.peerevalbackend.admin.dto;

public class SearchCriteriaDto {
    private String sectionName;
    private String academicYear;

    // Constructors, getters, and setters
    public SearchCriteriaDto() {}

    public SearchCriteriaDto(String sectionName, String academicYear) {
        this.sectionName = sectionName;
        this.academicYear = academicYear;
    }

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
}

