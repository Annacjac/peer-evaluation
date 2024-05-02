package edu.tcu.cs.peerevalbackend.admin.Email;

import java.util.List;

public class EmailRequest {

    private List<String> instructorEmails;
    private String message;

    // Getters and setters
    public List<String> getInstructorEmails() {
        return instructorEmails;
    }

    public void setInstructorEmails(List<String> instructorEmails) {
        this.instructorEmails = instructorEmails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
