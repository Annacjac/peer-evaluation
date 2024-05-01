package edu.tcu.cs.peerevalbackend.instructor.dto;

import jakarta.validation.constraints.NotEmpty;

public record InstructorDto(
        Integer id,
        @NotEmpty(message = "First name is required.") String firstName,
        @NotEmpty(message = "Last name is required.") String lastName,
        @NotEmpty(message = "Email is required.") String email
        /*, Set<CourseDto> courses*/) {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return null;
    }
}
