package edu.tcu.cs.peerevalbackend.student.dto;

import jakarta.validation.constraints.NotEmpty;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;



public record StudentDto (@NotEmpty(message = "Email is required") String email,
                          @NotEmpty(message = "First name is required") String firstName,
                          char midInit,
                          @NotEmpty(message = "Last name is required") String lastName,
                          @NotEmpty(message = "Password is required") String password,
                          SeniorDesignTeamDto team) {

}
