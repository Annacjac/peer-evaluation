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

    @PostMapping("/sendInvitations")
    public ResponseEntity<String> sendEmail(@RequestBody List<String> instructorEmails) throws MessagingException {
        emailService.sendEmail(instructorEmails);
        return ResponseEntity.ok("Invitation emails sent successfully!");
    }
}