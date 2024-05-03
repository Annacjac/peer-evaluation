package edu.tcu.cs.peerevalbackend.admin.Email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Endpoint to send a default email to a list of instructor emails
    @PostMapping("/sendInvitations")
    public ResponseEntity<String> sendEmail(@RequestBody List<String> instructorEmails) throws MessagingException {
        try {
            emailService.sendEmail(instructorEmails);
            return ResponseEntity.ok("Invitation emails sent successfully!");
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Failed to send invitation emails: " + e.getMessage());
        }
    }

    // Endpoint to send a custom email to a list of instructor emails
    @PostMapping("/sendCustomInvitations")
    public ResponseEntity<String> sendCustomEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        try {
            emailService.sendCustomEmail(emailRequest.getInstructorEmails(), emailRequest.getMessage());
            return ResponseEntity.ok("Custom invitation emails sent successfully!");
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Failed to send custom invitation emails: " + e.getMessage());
        }
    }
}