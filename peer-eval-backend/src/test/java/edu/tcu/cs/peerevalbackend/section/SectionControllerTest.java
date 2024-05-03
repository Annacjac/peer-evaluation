package edu.tcu.cs.peerevalbackend.section;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import edu.tcu.cs.peerevalbackend.section.SectionCreateRequestDto;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.http.MediaType.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionService sectionService;

    @Test
    public void testCreateSectionSuccess() throws Exception {
        SectionCreateRequestDto request = new SectionCreateRequestDto();
        request.setSectionName("New Section 2024");
        request.setAcademicYear("2024-2025");
        request.setRubricId(1);

        Section expectedSection = new Section();
        expectedSection.setId(1);
        expectedSection.setSectionName(request.getSectionName());
        expectedSection.setAcademicYear(request.getAcademicYear());

        given(sectionService.createSection(anyString(), anyString(), anyInt())).willReturn(expectedSection);

        mockMvc.perform(post("/sections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))) // You need to convert your request object to JSON string
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sectionName").value("New Section 2024"));
    }

    // Utility method to convert object to JSON string
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
