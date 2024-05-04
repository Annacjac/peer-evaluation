package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstructorController.class)
class InstructorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstructorService instructorService;

    @Test
    void testRegisterInstructor() throws Exception {
        InstructorDto instructorDto = new InstructorDto(null, "Alice", "Wonderland", "alice@example.com");
        InstructorDto savedInstructorDto = new InstructorDto(1, "Alice", "Wonderland", "alice@example.com");

        when(instructorService.createInstructor(any(InstructorDto.class))).thenReturn(savedInstructorDto);

        mockMvc.perform(post("/instructors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(instructorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"))
                .andExpect(jsonPath("$.lastName").value("Wonderland"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void testRemoveInstructor_Success() throws Exception {
        doNothing().when(instructorService).removeInstructorFromTeam(any(Long.class), any(Long.class));
        mockMvc.perform(delete("/api/instructors/remove-from-team/1/1"))
                .andExpect(status().isOk());
    }

    /*
    @Test
    void testRemoveInstructor_Failure() throws Exception {
        doThrow(new RuntimeException("Failed to remove instructor")).when(instructorService).removeInstructorFromTeam(any(Long.class), any(Long.class));
        mockMvc.perform(delete("/api/instructors/remove-from-team/1/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Failed to remove instructor")));
    }

    @Test
    void testFindInstructors_Success() throws Exception {
        when(instructorService.findInstructors(any(InstructorSearchDto.class))).thenReturn(List.of(new Instructor()));
        mockMvc.perform(post("/api/instructors/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"lastName\":\"Doe\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testViewInstructor_Success() throws Exception {
        when(instructorService.getInstructorById("1")).thenReturn(new InstructorDto());
        mockMvc.perform(get("/api/instructors/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeactivateInstructor_Success() throws Exception {
        doNothing().when(instructorService).deactivateInstructor("1");
        mockMvc.perform(put("/api/instructors/deactivate/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testReactivateInstructor_Success() throws Exception {
        doNothing().when(instructorService).reactivateInstructor("1");
        mockMvc.perform(put("/api/instructors/reactivate/1"))
                .andExpect(status().isOk());
    }

     */
}