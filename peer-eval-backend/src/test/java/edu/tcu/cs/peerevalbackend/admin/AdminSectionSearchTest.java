package edu.tcu.cs.peerevalbackend.admin;

import edu.tcu.cs.peerevalbackend.admin.dto.SearchCriteriaDto;
import edu.tcu.cs.peerevalbackend.section.Section;
import edu.tcu.cs.peerevalbackend.section.SectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminSectionSearchTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testFindSections() throws Exception {
        // Setup
        SearchCriteriaDto criteria = new SearchCriteriaDto("Engineering", "2022");
        Section section = new Section(); // Set the necessary properties if needed
        Page<Section> expectedPage = new PageImpl<>(Arrays.asList(section));
        Pageable pageable = PageRequest.of(0, 10);

        // Mocking
        when(sectionRepository.findByCriteria(criteria.getSectionName(), criteria.getAcademicYear(), pageable)).thenReturn(expectedPage);

        // Execution
        Page<Section> resultPage = adminService.findSections(criteria, pageable);

        // Assertion
        assertEquals(expectedPage.getTotalElements(), resultPage.getTotalElements(), "The number of sections should match");
        assertEquals(1, resultPage.getTotalElements(), "Expected one section to be found");
    }
}

