package edu.tcu.cs.peerevalbackend.section;

//import edu.tcu.cs.peerevalbackend.service.SectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(SectionController.class)
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionService sectionService;

    @Test
    public void testSearchSections() throws Exception {
        // Set up mock behavior


        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/sections/search")
                        .param("sectionName", "name")
                        .param("academicYear", "2023-2024")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        // Verify interactions
        verify(sectionService).findSections("name", "2023-2024");
    }
}