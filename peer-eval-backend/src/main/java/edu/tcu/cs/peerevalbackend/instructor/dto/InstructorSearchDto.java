package edu.tcu.cs.peerevalbackend.instructor.dto;

import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;

public class InstructorSearchDto {
    private String firstName;
    private String lastName;
    private String email;

    // Default Constructor
    public InstructorSearchDto() {}

    // Constructor with parameters
    public InstructorSearchDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void assignInstructorsToTeams(SeniorDesignTeamDto dto) {
    }
}
