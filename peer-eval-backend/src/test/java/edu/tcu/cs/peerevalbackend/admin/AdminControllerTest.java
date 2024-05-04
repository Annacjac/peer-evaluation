package edu.tcu.cs.peerevalbackend.admin;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import edu.tcu.cs.peerevalbackend.admin.dto.AdminDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;


    private MockMvc mockMvc;

    @Test
    void testInviteInstructors_Success() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmails("email@example.com;email2@example.com");
        adminDto.setAdminName("Admin");

        doNothing().when(adminService).sendInvitations(any(AdminDto.class));
        ResponseEntity<?> response = adminController.inviteInstructors(adminDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("Invitations sent successfully!");
    }

    @Test
    void testInviteInstructors_Failure() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmails("email@example.com;email2@example.com");
        adminDto.setAdminName("Admin");

        doThrow(new RuntimeException("Error sending invitations")).when(adminService).sendInvitations(any(AdminDto.class));
        Exception exception = assertThrows(RuntimeException.class, () -> adminController.inviteInstructors(adminDto));
        assertThat(exception.getMessage()).contains("Error sending invitations");
    }
/*
    @Test
    void testAssignInstructors_Success() throws Exception {
        mockMvc.perform(post("/admin/assign-instructors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"teamId\":\"1\",\"instructorIds\":[\"1\",\"2\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Instructors assigned successfully")));
    }

 */
}