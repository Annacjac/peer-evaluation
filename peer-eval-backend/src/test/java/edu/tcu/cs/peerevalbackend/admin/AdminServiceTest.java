package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.Email.EmailService;
import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private EmailService emailService;

    private AdminDto adminDto;

    @BeforeEach
    void setUp() {
        // Initialize your AdminDto here with test data
        adminDto = new AdminDto();
        adminDto.setAdminName("Allen Admin");
        adminDto.setEmails("example@example.com;");
        adminDto.setAdminEmail("allentcu@gmail.com");
        adminDto.setMessage("Test message");
    }

    @Test
    void testSendInvitations() throws Exception {
        // Simulate the behavior of sendEmailToRecipient to ensure it's called correctly
        doNothing().when(emailService).sendEmailToRecipient(anyString(), anyString(), anyString());

        // Call the method to test
        adminService.sendInvitations(adminDto);

        // Verify that sendEmailToRecipient was called the correct number of times and with correct parameters
        verify(emailService, times(1)).sendEmailToRecipient(
                eq("example@example.com"),
                eq("Welcome to The Peer Evaluation Tool - Complete Your Registration"),
                anyString());

        // You can add more checks here to validate the behavior further
    }
}