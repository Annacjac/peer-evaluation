package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.rubric.Rubric;
import edu.tcu.cs.peerevalbackend.rubric.RubricRepository;
import edu.tcu.cs.peerevalbackend.system.exception.ObjectNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SectionServiceTest {
    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private RubricRepository rubricRepository;

    @InjectMocks
    private SectionService sectionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public void testCreateSectionServiceSuccess() {
        String name = "Test Section";
        String year = "2023-2024";
        Integer rubricId = 1;
        Rubric rubric = new Rubric();
        rubric.setId(rubricId);

        when(rubricRepository.findById(rubricId)).thenReturn(Optional.of(rubric));
        when(sectionRepository.existsBySectionName(name)).thenReturn(false);
        when(sectionRepository.save(any(Section.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Section section = sectionService.createSection(name, year, rubricId);

        assertNotNull(section);
        assertEquals(name, section.getSectionName());
        assertEquals(year, section.getAcademicYear());
        assertEquals(rubric, section.getRubric());
    }
    @Test
    public void testCreateSectionServiceDuplicateName() {
        String name = "Existing Section";
        String year = "2023-2024";
        Integer rubricId = 1;

        when(sectionRepository.existsBySectionName(name)).thenReturn(true);

        sectionService.createSection(name, year, rubricId);
    }
    @Test
    public void testCreateSectionServiceRubricNotFound() {
        String name = "Test Section";
        String year = "2023-2024";
        Integer rubricId = 99;  // Assuming this ID does not exist

        when(rubricRepository.findById(rubricId)).thenReturn(Optional.empty());

        ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
            sectionService.createSection(name, year, rubricId);
        });

        assertTrue(thrown.getMessage().contains("Rubric not found with ID: " + rubricId));
    }
}
