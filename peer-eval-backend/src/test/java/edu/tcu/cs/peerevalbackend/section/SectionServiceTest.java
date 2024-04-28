package edu.tcu.cs.peerevalbackend.section;

import edu.tcu.cs.peerevalbackend.section.Section;
//import edu.tcu.cs.peerevalbackend.repository.SectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SectionServiceTest {

    @Autowired
    private SectionService sectionService;

    @MockBean
    private SectionRepository sectionRepository;

    @Test
    public void testFindSections() {
        // Arrange
        Section section1 = new Section();
        section1.setId(1);
        section1.setAcademicYear("2023-2024");

        Section section2 = new Section();
        section2.setId(2);
        section2.setAcademicYear("2022-2023");

        when(sectionRepository.findBySectionNameAndAcademicYear("name", "2023-2024", Sort.by(Sort.Order.desc("academicYear"), Sort.Order.asc("sectionName"))))
                .thenReturn(Arrays.asList(section1, section2));

        // Act
        List<Section> results = sectionService.findSections("name", "2023-2024");

        // Assert
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getAcademicYear()).isEqualTo("2023-2024");
        verify(sectionRepository).findBySectionNameAndAcademicYear("name", "2023-2024", Sort.by(Sort.Order.desc("academicYear"), Sort.Order.asc("sectionName")));
    }
}
