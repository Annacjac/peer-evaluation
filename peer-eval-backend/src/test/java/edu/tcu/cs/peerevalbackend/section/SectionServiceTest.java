package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SectionServiceTest {

    @InjectMocks
    private SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    @Test
    public void testSearchSections() {
        MockitoAnnotations.initMocks(this);

        // Setup
        String sectionName = "Software Engineering";
        String academicYear = "2022-2023";
        List<Section> expectedSections = new ArrayList<>();
        expectedSections.add(new Section()); // Add a mock Section with relevant details
        when(sectionRepository.findAll((Sort) any(Specification.class))).thenReturn(expectedSections);

        // Execution
        List<Section> foundSections = sectionService.searchSections(sectionName, academicYear);

        // Verification
        assertFalse(foundSections.isEmpty());
        assertEquals(expectedSections.size(), foundSections.size());
        verify(sectionRepository).findAll((Sort) any(Specification.class));
    }

    @Test
    public void testAddSection() {
        MockitoAnnotations.initMocks(this);

        // Setup
        Section newSection = new Section();
        newSection.setSectionName("Advanced Databases");
        newSection.setAcademicYear("2023-2024");
        when(sectionRepository.save(any(Section.class))).thenReturn(newSection);

        // Execution
        Section savedSection = sectionService.save(newSection);

        // Verification
        assertNotNull(savedSection);
        assertEquals("Advanced Databases", savedSection.getSectionName());
        assertEquals("2023-2024", savedSection.getAcademicYear());
        verify(sectionRepository).save(any(Section.class));
    }

}

