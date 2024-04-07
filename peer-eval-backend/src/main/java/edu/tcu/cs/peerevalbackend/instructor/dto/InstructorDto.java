package edu.tcu.cs.peerevalbackend.instructor.dto;

import jakarta.validation.constraints.NotEmpty;

public record InstructorDto(
        String id,
        @NotEmpty(message = "First name is required.") String firstName,
        char midInit,
        @NotEmpty(message = "Last name is required.") String lastName,
        @NotEmpty(message = "Email is required.") String email
        /*, Set<CourseDto> courses*/) {
    
}
