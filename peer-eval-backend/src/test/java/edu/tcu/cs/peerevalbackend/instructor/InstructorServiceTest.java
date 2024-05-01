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
import java.util.Arrays;
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
        SeniorDesignTeam team = new SeniorDesignTeam();
        Instructor instructor = new Instructor();
        instructor.setId(Integer.parseInt("instructorId"));
        team.setInstructors(Arrays.asList(instructor));

        when(seniorDesignTeamRepository.findById("teamId")).thenReturn(Optional.of(team));

        service.removeInstructorFromTeam("teamId", "instructorId");

        assertTrue(team.getInstructors().isEmpty());
        verify(seniorDesignTeamRepository).save(team);
    }

    @Test
    void testFindInstructorsByCriteria() {
        InstructorSearchDto criteria = new InstructorSearchDto("John", null, null);
        List<Instructor> expectedInstructors = Arrays.asList(new Instructor());
        when(instructorRepository.findByFirstNameContainingIgnoreCase("John")).thenReturn(expectedInstructors);

        List<Instructor> result = service.findInstructors(criteria);

        assertEquals(expectedInstructors, result);
    }

    @Test
    void testGetInstructorById_Found() {
        Instructor expectedInstructor = new Instructor();
        expectedInstructor.setId("1");
        when(instructorRepository.findById("1")).thenReturn(Optional.of(expectedInstructor));

        InstructorDto result = service.getInstructorById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
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
