package edu.tcu.cs.peerevalbackend.instructor;


import edu.tcu.cs.peerevalbackend.instructor.Instructor;
import edu.tcu.cs.peerevalbackend.instructor.InstructorRepository;
import edu.tcu.cs.peerevalbackend.instructor.dto.InstructorSearchDto;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeam;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.SeniorDesignTeamRepository;
import edu.tcu.cs.peerevalbackend.seniorDesignTeam.dto.SeniorDesignTeamDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorAssignmentServiceTest {

    @InjectMocks
    private InstructorSearchDto service;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private SeniorDesignTeamRepository seniorDesignTeamRepository;

    @BeforeEach
    void setUp() {
        // Setup necessary for each test
    }

//    @Test
//    void testAssignInstructorsToTeams() {
//        // Mocking Data
//        Instructor instructor = new Instructor();
//        instructor.setId("instructorId");
//        SeniorDesignTeam team = new SeniorDesignTeam();
//        team.setId("teamId");
//        Assignment assignment = new Assignment("teamId", "instructorId");
//
//        SeniorDesignTeamDto dto = new SeniorDesignTeamDto();
//        dto.setAssignments(Arrays.asList(assignment));
//
//        when(instructorRepository.findById("instructorId")).thenReturn(Optional.of(instructor));
//        when(seniorDesignTeamRepository.findById("teamId")).thenReturn(Optional.of(team));
//
//        // Action
//        service.assignInstructorsToTeams(dto);
//
//        // Verification
//        assertTrue(team.getInstructors().contains(instructor));
//        verify(seniorDesignTeamRepository).save(team);
//    }

//    @Test
//    void testAssignInstructorsToTeams_InstructorOrTeamNotFound() {
//        SeniorDesignTeamDto.Assignment assignment = new SeniorDesignTeamDto.Assignment("teamId", "invalidInstructorId");
//        SeniorDesignTeamDto dto = new SeniorDesignTeamDto();
//        dto.setAssignments(Arrays.asList(assignment));
//
//        when(instructorRepository.findById("invalidInstructorId")).thenReturn(Optional.empty());
//        when(seniorDesignTeamRepository.findById("teamId")).thenReturn(Optional.of(new SeniorDesignTeam()));
//
//        // Expecting an exception due to not finding the instructor
//        Exception exception = assertThrows(RuntimeException.class, () -> service.assignInstructorsToTeams(dto));
//
//        assertTrue(exception.getMessage().contains("Instructor or Team not found"));
//    }
}