package edu.tcu.cs.peerevalbackend.admin.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/inviteInstructors")
    public ResponseEntity<String> sendEmail(@RequestBody List<String> instructorEmails) {
        if (instructorEmails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email provided");
        }

        // Validate email format
        for (String email : instructorEmails) {
            if (!emailService.validateEmail(email)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format: " + email);
            }
        }

        // Generate registration links
        List<String> registrationLinks = emailService.generateRegistrationLinks(instructorEmails);

        // Prepare and send invitation emails

        String recipient =
        String subject = "Welcome to The Peer Evaluation Tool - Complete Your Registration";
        String message = "Hello,\n\n[Name of the Admin] has invited you to join The Peer Evaluation Tool. " +
                "To complete your registration, please use the link below:\n\n[Registration link]\n\n" +
                "If you have any questions or need assistance, feel free to contact [Admin’s email] or our team directly.\n\n" +
                "Please note: This email is not monitored, so do not reply directly to this message.\n\nBest regards,\nPeer Evaluation Tool Team";

        emailService.sendInvitationEmails(instructorEmails, subject, message);

        return ResponseEntity.ok("Invitation emails sent successfully!");
    }
}