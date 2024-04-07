package edu.tcu.cs.peerevalbackend.student.dto;

import jakarta.validation.constraints.NotEmpty;


public record StudentDto(String id, @NotEmpty(message = "First name is required.") String name, char midInit, @NotEmpty(message = "Last name is required.") String lastName, @NotEmpty(message = "Password is required.") String password/*, GroupDto team*/) {
    
}
