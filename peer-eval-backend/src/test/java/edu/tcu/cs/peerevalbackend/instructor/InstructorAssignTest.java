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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testAssignInstructorsToTeams() {
        // Given
        Long instructorId = 1L;
        Long teamId = 1L;
        Instructor instructor = new Instructor();
        instructor.setId(instructorId);
        SeniorDesignTeam team = new SeniorDesignTeam();
        team.setId(teamId);

        when(instructorRepository.findById(String.valueOf(instructorId))).thenReturn(Optional.of(instructor));
        when(seniorDesignTeamRepository.findById(teamId)).thenReturn(Optional.of(team));

        SeniorDesignTeamDto dto = new SeniorDesignTeamDto();
        dto.setAssignments(List.of(new SeniorDesignTeamDto.Assignment(instructorId, teamId)));

        // When
        service.assignInstructorsToTeams(dto);

        // Then
        verify(seniorDesignTeamRepository).save(teamCaptor.capture());
        SeniorDesignTeam savedTeam = teamCaptor.getValue();
        assertEquals(instructorId, savedTeam.getInstructor().getId());
    }
}