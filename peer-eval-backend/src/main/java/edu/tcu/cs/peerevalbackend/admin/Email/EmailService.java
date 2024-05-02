package edu.tcu.cs.peerevalbackend.admin.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(List<String> instructorEmails) throws MessagingException {
        String subject = "Welcome to The Peer Evaluation Tool - Complete Your Registration";
        String message = buildEmailMessage();

        for (String email : instructorEmails) {
            sendEmailToRecipient(email, subject, message);
        }
    }

    public void sendCustomEmail(List<String> instructorEmails, String customMessage) throws MessagingException {
        String subject = "Custom Invitation - Peer Evaluation Tool";

        for (String email : instructorEmails) {
            sendEmailToRecipient(email, subject, customMessage);
        }
    }

    public void sendEmailToRecipient(String email, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(text, true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setFrom("noreply@peereval.com");
        mailSender.send(mimeMessage);
    }

    private String buildEmailMessage() {
        return "Hello,\n\n" +
                "[Name of the Admin] has invited you to join The Peer Evaluation Tool. " +
                "To complete your registration, please use the link below:\n\n" +
                "[Registration link]\n\n" +
                "If you have any questions or need assistance, feel free to contact [Admin’s email] or our team directly.\n\n" +
                "Please note: This email is not monitored, so do not reply directly to this message.\n\n" +
                "Best regards,\n" +
                "Peer Evaluation Tool Team";
    }
}