package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        // This can be used to set up any necessary data or state before each test
    }

    @Test
    void testSendInvitations() {
        // Given
        String emails = "john.doe@example.com;jane.doe@example.com";
        String message = "Welcome to the peer evaluation tool.";
        AdminDto adminDto = new AdminDto(emails, message);

        // When
        adminService.sendInvitations(adminDto);

        // Then
        verify(emailService, times(2)).sendEmail(anyString(), eq(message));
    }
}