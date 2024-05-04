/*package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.SectionController;
import edu.tcu.cs.peerevalbackend.section.dto.SectionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@WebMvcTest(SectionController.class)
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionService sectionService;

    @InjectMocks
    private SectionController sectionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(sectionController).build();
    }
    @Test
    public void testSectionSearch() throws Exception {
        // Given
        List<SectionDto> sectionDtos = List.of(new SectionDto("1", "Software Engineering", "2023", new ArrayList<>(), null, new ArrayList<>(), null));
        when(sectionService.searchSections("Software Engineering", "2023")).thenReturn(sectionDtos);

        // When & Then
        mockMvc.perform(get("/section_search")
                        .param("sectionName", "Software Engineering")
                        .param("academicYear", "2023")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.data[0].sectionName").value("Software Engineering"))
                .andExpect(jsonPath("$.data[0].academicYear").value("2023"));

        verify(sectionService).searchSections("Software Engineering", "2023");
    }
    @Test
    public void testAddSection() throws Exception {
        // Setup
        SectionDto sectionDto = new SectionDto("1", "Machine Learning", "2024", new ArrayList<>(), null, new ArrayList<>(), null);
        SectionDto savedSectionDto = new SectionDto("1", "Machine Learning", "2024", new ArrayList<>(), null, new ArrayList<>(), null);
        when(sectionService.save(any(Section.class))).thenReturn(savedSectionDto);

        // Execution & Verification
        mockMvc.perform(post("/section")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"sectionName\":\"Machine Learning\",\"academicYear\":\"2024\",\"students\":[],\"instructor\":null,\"teams\":[],\"rubric\":null}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sectionName").value("Machine Learning"))
                .andExpect(jsonPath("$.data.academicYear").value("2024"));

        verify(sectionService).save(any(Section.class));
    }

}
*/