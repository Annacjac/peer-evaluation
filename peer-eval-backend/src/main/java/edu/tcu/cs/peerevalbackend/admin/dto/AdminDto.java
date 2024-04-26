package edu.tcu.cs.peerevalbackend.admin.dto;

public class AdminDto {
    private String emails;
    private String message;
    private String adminName;
    private String adminEmail;

    // Getters and Setters
    public String getEmails() {

        return emails;
    }

    public void setEmails(String emails) {

        this.emails = emails;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminEmail(String mail) {
    }
}
