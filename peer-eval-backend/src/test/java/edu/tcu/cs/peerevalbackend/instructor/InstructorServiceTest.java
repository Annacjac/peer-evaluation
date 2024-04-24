package edu.tcu.cs.peerevalbackend.instructor;

import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorDto;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.instructor.service.InstructorService;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private SeniorDesignTeamRepository seniorDesignTeamRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRemoveInstructorFromTeam() {
        // Given
        Long teamId = 1L;
        Long instructorId = 1L;
        Instructor instructor = new Instructor();
        instructor.setId(instructorId);
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setId(teamId);
        team.setInstructor(instructor);

        when(seniorDesignTeamRepository.findById(teamId)).thenReturn(Optional.of(team));

        // When
        instructorService.removeInstructorFromTeam(teamId, instructorId);

        // Then
        verify(seniorDesignTeamRepository).save(teamCaptor.capture());
        SeniorDesignTeam savedTeam = teamCaptor.getValue();
        assertNull(savedTeam.getInstructor()); // Verify the instructor is removed
    }

    @Test
    void testFindInstructorsByCriteria() {
        // Given
        InstructorSearchDto criteria = new InstructorSearchDto();
        criteria.setFirstName("John");
        criteria.setLastName("Doe");

        List<Instructor> expectedInstructors = new ArrayList<>();
        expectedInstructors.add(new Instructor("1", "John", 'M', "Doe", "john.doe@example.com"));

        when(instructorRepository.findAll(any(Specification.class))).thenReturn(findInstructors);

        // When
        List<Instructor> actualInstructors = instructorService.findInstructors(criteria);

        // Then
        verify(instructorRepository).findAll(any(Specification.class)); // Verifies that findAll was called with a specification
        assertEquals(expectedInstructors, actualInstructors, "The returned list of instructors should match the expected list");
    }

    @Test
    void testGetInstructorById_Found() {
        // Given
        String id = "1";
        Instructor expectedInstructor = new Instructor("1", "John", 'M', "Doe", "john.doe@example.com");
        when(instructorRepository.findById(id)).thenReturn(Optional.of(expectedInstructor));

        // When
        InstructorDto result = instructorService.getInstructorById(id);

        // Then
        verify(instructorRepository).findById(id);
        assertNotNull(result);
        assertEquals("John", result.getFirstName(), "Instructor first name should match");
        assertEquals("Doe", result.getLastName(), "Instructor last name should match");
    }

    @Test
    void testGetInstructorById_NotFound() {
        // Given
        String id = "nonexistent";
        when(instructorRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            instructorService.getInstructorById(id);
        });

        assertEquals("Instructor not found", exception.getMessage());
    }

    @Test
    void testDeactivateInstructor_Success() {
        // Given
        String id = "1";
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setActive(true);
        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        // When
        instructorService.deactivateInstructor(id);

        // Then
        assertFalse(instructor.isActive(), "Instructor should be deactivated");
        verify(instructorRepository).save(instructor);
    }

    @Test
    void testDeactivateInstructor_AlreadyInactive() {
        // Given
        String id = "1";
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setActive(false);
        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        // When & Then
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            instructorService.deactivateInstructor(id);
        });

        assertEquals("Instructor is already inactive.", exception.getMessage());
    }

    @Test
    void testDeactivateInstructor_NotFound() {
        // Given
        String id = "nonexistent";
        when(instructorRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            instructorService.deactivateInstructor(id);
        });

        assertEquals("Instructor not found", exception.getMessage());
    }

    @Test
    void testReactivateInstructor_Success() {
        // Given
        String id = "1";
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setActive(false);
        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        // When
        instructorService.reactivateInstructor(id);

        // Then
        assertTrue(instructor.isActive(), "Instructor should be reactivated");
        verify(instructorRepository).save(instructor);
    }

    @Test
    void testReactivateInstructor_AlreadyActive() {
        // Given
        String id = "1";
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setActive(true);
        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        // When & Then
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            instructorService.reactivateInstructor(id);
        });

        assertEquals("Instructor is already active.", exception.getMessage());
    }

    @Test
    void testReactivateInstructor_NotFound() {
        // Given
        String id = "nonexistent";
        when(instructorRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            instructorService.reactivateInstructor(id);
        });

        assertEquals("Instructor not found", exception.getMessage());
    }
}
