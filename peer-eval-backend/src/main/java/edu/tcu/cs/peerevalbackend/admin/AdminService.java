package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private EmailService emailService;  // Assume EmailService handles the email sending

    public void sendInvitations(AdminDto adminDto) throws Exception {
        String[] emails = adminDto.getEmails().split(";");
        for (String email : emails) {
            if (!email.trim().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
                throw new Exception("Invalid email format: " + email);
            }
            emailService.sendEmail(recipient, subject, message);
        }
    }
}