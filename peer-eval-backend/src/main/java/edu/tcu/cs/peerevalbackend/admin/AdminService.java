package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import edu.tcu.cs.peerevalbackend.admin.dto.SearchCriteriaDto;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private EmailService emailService;

    public void sendInvitations(AdminDto adminDto) throws Exception {
        String[] emails = adminDto.getEmails().split(";");
        String adminName = adminDto.getAdminName();

        for (String email : emails) {
            String trimmedEmail = email.trim();
            if (!isValidEmail(trimmedEmail)) {
                throw new Exception("Invalid email format: " + trimmedEmail);
            }
            String registrationLink = generateRegistrationLink(trimmedEmail);
            String message = buildEmailMessage(adminName, registrationLink);
            String subject = "Welcome to The Peer Evaluation Tool - Complete Your Registration";

            emailService.sendEmailToRecipient(trimmedEmail, subject, message);
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
    }

    private String generateRegistrationLink(String email) {
        // Generate a UUID based on the user's email for uniqueness
        String token = UUID.nameUUIDFromBytes(email.getBytes()).toString();
        return "https://example.com/register?token=" + token;
    }

    private String buildEmailMessage(String adminName, String registrationLink) {
        return "Hello,\n\n" + adminName + " has invited you to join The Peer Evaluation Tool. " +
                "To complete your registration, please use the link below:\n\n" + registrationLink + "\n\n" +
                "If you have any questions or need assistance, feel free to contact [Admin’s email] or our team directly.\n\n" +
                "Please note: This email is not monitored, so do not reply directly to this message.\n\nBest regards,\n" +
                "Peer Evaluation Tool Team";
    }

    @Autowired
    private SectionRepository sectionRepository;

    public Page<Section> findSections(SearchCriteriaDto criteria, Pageable pageable) throws Exception {
        return sectionRepository.findByCriteria(criteria.getSectionName(), criteria.getAcademicYear(), pageable);
    }
}